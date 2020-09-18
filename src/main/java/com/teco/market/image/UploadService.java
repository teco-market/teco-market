package com.teco.market.image;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String upload(MultipartFile multipartFile, String path);
}
