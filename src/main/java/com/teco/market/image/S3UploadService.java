package com.teco.market.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3UploadService implements UploadService{
    @Override
    public String upload(MultipartFile multipartFile) {
        return null;
    }

    @Override
    public String uploadThumbnail(MultipartFile multipartFile) {
        return null;
    }
}
