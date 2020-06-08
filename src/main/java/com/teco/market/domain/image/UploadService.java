package com.teco.market.domain.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UploadService {

    String upload(MultipartFile multipartFile);

    String uploadThumbnail(MultipartFile multipartFile);
}
