package com.teco.market.image;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.teco.market.common.exception.UploadFailureException;
import com.teco.market.common.util.StringUtil;

@Component
public class S3UploadService implements UploadService {
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostConstruct
    public void init() {
        amazonS3 = AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .build();
    }

    @Override
    public String upload(MultipartFile file, String path) {
        String fileName = StringUtil.getRandomSHA256(Objects.requireNonNull(file.getOriginalFilename()));
        String key = String.format("%s%s.%s", path, fileName, StringUtil.getExtension(file.getOriginalFilename()));

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest(bucket, key, file.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (SdkClientException | IOException e) {
            throw new UploadFailureException();
        }

        return amazonS3.getUrl(bucket, key).toString();
    }
}
