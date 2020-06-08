package com.teco.market.domain.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.teco.market.exception.InvalidMultiFileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;
    private static final String BASIC_DIRECTORY = "static";
    private static final String BASIC_THUMBNAIL_DIRECTORY = "thumbnail";

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile) {
        File uploadFile = null;
        try {
            uploadFile = convert(multipartFile)
                .orElseThrow(InvalidMultiFileException::new);
        } catch (IOException e) {
            throw new InvalidMultiFileException();
        }

        return upload(uploadFile, BASIC_DIRECTORY);
    }

    public String uploadThumbnail(MultipartFile multipartFile) {
        try {
            File file = convert(multipartFile)
                .orElseThrow(InvalidMultiFileException::new);
            File thumbnail = Thumbnails.of(file)
                .size(300, 300)
                .outputFormat("png")
                .asFiles(Rename.PREFIX_HYPHEN_THUMBNAIL)
                .get(0);
            return upload(thumbnail, BASIC_THUMBNAIL_DIRECTORY);
        } catch (IOException e) {
            throw new InvalidMultiFileException();
        }
    }

    private Optional<File> convert(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        if (convertedFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
                fos.write(multipartFile.getBytes());
            }
            return Optional.of(convertedFile);
        }
        return Optional.empty();
    }

    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private void removeNewFile(File targetFile) {
        log.info(targetFile.delete() ? targetFile.getName() + "파일을 삭제하였습니다." : targetFile.getName() + "파일이 삭제되지 못했습니다.");
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client
            .putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }
}
