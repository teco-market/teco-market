package com.teco.market.image;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

@Component
public class S3UploadService implements UploadService {
    private static final String SHA_256 = "SHA-256";

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
        String fileName = getRandomSHA256(Objects.requireNonNull(file.getOriginalFilename()));
        String key = String.format("%s%s.%s", path, fileName, getExtension(file.getOriginalFilename()));

        try {
            fetchUploadToS3(file, key);
        } catch (SdkClientException | IOException e) {
            throw new UploadFailureException();
        }

        return amazonS3.getUrl(bucket, key).toString();
    }

    private String getExtension(String name) {
        if (name == null) {
            return null;
        }
        int index = indexOfExtension(name);
        if (index == -1) {
            return "";
        } else {
            return name.substring(index + 1);
        }
    }

    private int indexOfExtension(String name) {
        if (name == null) {
            return -1;
        }
        int extensionPos = name.lastIndexOf(".");
        int lastSeparator = indexOfLastSeparator(name);
        return ((lastSeparator > extensionPos) ? -1 : extensionPos);
    }

    private int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        int lastUnixPos = filename.lastIndexOf("/");
        int lastWindowsPos = filename.lastIndexOf("/");
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    private void fetchUploadToS3(MultipartFile file, String key) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        amazonS3.putObject(new PutObjectRequest(bucket, key, file.getInputStream(), metadata)
            .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String getRandomSHA256(final String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance(SHA_256);
            byte[] encodedhash = digest.digest(
                message.getBytes(StandardCharsets.UTF_8));

            return byteArrayToString(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("SHA-256 algorithm should exists in MessageDigest");
        }
    }

    private String byteArrayToString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }

        return stringBuilder.toString();
    }
}
