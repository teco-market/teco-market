package com.teco.market.image;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.teco.market.common.exception.invalid.InvalidMultiFileException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Service
public class S3UploaderService implements UploadService {
    private AmazonS3 amazonS3;
    private static final String BASIC_DIRECTORY = "static";
    private static final String BASIC_THUMBNAIL_DIRECTORY = "thumbnail";

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void S3UploaderService() {
        amazonS3 = AmazonS3ClientBuilder.standard()
            .withRegion(this.region)
            .build();
    }

    public String upload(MultipartFile multipartFile) {
        File file = convert(multipartFile);
        PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(bucket, file.getName(), file)
            .withCannedAcl(CannedAccessControlList.PublicRead));
        String s = amazonS3.getUrl(bucket, file.getName()).toString();
        System.out.println(s);
        return s;
    }

    public String upload(File file) {
        amazonS3.putObject(
            new PutObjectRequest(bucket, file.getName(), file).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, file.getName()).toString();
    }

    @Override
    public String uploadThumbnail(MultipartFile multipartFile) {
        File convertedFile = convert(multipartFile);
        try {
            File thumbnail = Thumbnails.of(convertedFile)
                .size(300, 300)
                .outputFormat("jpeg")
                .asFiles(Rename.PREFIX_HYPHEN_THUMBNAIL)
                .get(0);
            return upload(thumbnail);
        } catch (IOException e) {
            throw new InvalidMultiFileException();
        }
    }

    private File convert(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
            return file;
        } catch (IOException e) {
            throw new InvalidMultiFileException();
        }
    }

    // public String uploadThumbnail(MultipartFile multipartFile) {
    //     File file = convert(multipartFile)
    //         .orElseThrow(InvalidMultiFileException::new);
    //     File thumbnail = null;
    //     try {
    //         thumbnail = Thumbnails.of(file)
    //             .size(300, 300)
    //             .outputFormat("png")
    //             .asFiles(Rename.PREFIX_HYPHEN_THUMBNAIL)
    //             .get(0);
    //     } catch (IOException e) {
    //         log.error(ErrorType.INVALID_IMAGE.getMessage());
    //         throw new InvalidMultiFileException();
    //     }
    //     return upload(thumbnail, BASIC_THUMBNAIL_DIRECTORY);
    //
    // }
    //
    // private Optional<File> convert(MultipartFile multipartFile) {
    //     File convertedFile = new File(multipartFile.getOriginalFilename());
    //     try {
    //         if (convertedFile.createNewFile()) {
    //             try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
    //                 fos.write(multipartFile.getBytes());
    //             }
    //             return Optional.of(convertedFile);
    //         }
    //     } catch (IOException e) {
    //         log.error(ErrorType.INVALID_IMAGE.getMessage());
    //         throw new InvalidMultiFileException();
    //     }
    //     return Optional.empty();
    // }
    //
    // private String upload(File uploadFile, String dirName) {
    //     String fileName = dirName + "/" + uploadFile.getName();
    //     String uploadImageUrl = putS3(uploadFile, fileName);
    //     removeNewFile(uploadFile);
    //     return uploadImageUrl;
    // }
    //
    // private void removeNewFile(File targetFile) {
    //     log.info(targetFile.delete()
    //         ? targetFile.getName() + "파일을 삭제하였습니다."
    //         : targetFile.getName() + "파일이 삭제되지 못했습니다.");
    // }
    //
    // private String putS3(File uploadFile, String fileName) {
    //     amazonS3Client
    //         .putObject(new PutObjectRequest(bucket, fileName, uploadFile)
    //             .withCannedAcl(CannedAccessControlList.PublicRead));
    //     return amazonS3Client.getUrl(bucket, fileName).toString();
    // }
}
