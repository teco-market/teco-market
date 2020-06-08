package com.teco.market.domain.post.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.teco.market.domain.category.Category;
import com.teco.market.domain.category.CategoryRepository;
import com.teco.market.domain.image.S3UploaderService;
import com.teco.market.domain.image.Thumbnail;
import com.teco.market.domain.image.UploadService;
import com.teco.market.domain.member.Member;
import com.teco.market.domain.post.Post;
import com.teco.market.domain.post.PostRepository;
import com.teco.market.exception.NotFoundCategoryException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class PostService {
    private static final int FIRST_PHOTO = 0;

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UploadService uploadService;

    public void save(PostRequest request, Member member) {
        Category category = categoryRepository.findById(request.getCategory())
            .orElseThrow(NotFoundCategoryException::new);

        Post post = Post.builder()
            .title(request.getTitle())
            .member(member)
            .price(BigDecimal.valueOf(request.getPrice()))
            .category(category)
            .content(request.getContent())
            .photos(savePhotos(request))
            .thumbnail(thumbnail(request.getMultipartFiles().get(FIRST_PHOTO)))
            .build();
        postRepository.save(post);
    }

    private Thumbnail thumbnail(MultipartFile file) {
        return new Thumbnail(uploadService.uploadThumbnail(file));
    }

    private List<String> savePhotos(PostRequest request) {
        return request.getMultipartFiles().stream()
            .map(uploadService::upload)
            .collect(Collectors.toList());
    }
}
