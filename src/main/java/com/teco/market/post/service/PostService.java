package com.teco.market.post.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.teco.market.category.Category;
import com.teco.market.category.CategoryRepository;
import com.teco.market.image.Thumbnail;
import com.teco.market.image.UploadService;
import com.teco.market.member.Member;
import com.teco.market.post.Post;
import com.teco.market.post.repository.PostRepository;
import com.teco.market.common.exception.invalid.InvalidWriterException;
import com.teco.market.common.exception.notfound.NotFoundCategoryException;
import com.teco.market.common.exception.notfound.NotFoundPostException;
import com.teco.market.post.web.PostRequest;
import com.teco.market.post.web.PostUpdateRequest;
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

    public void update(PostUpdateRequest request, Long postId, Member member) {
        Post findPost = postRepository.findById(postId)
            .orElseThrow(NotFoundPostException::new);
        validateWriter(member, findPost);
        findPost.changePost(request.getTitle(), request.getPrice(), request.getContent());
    }

    public void deleteById(Long postId, Member member) {
        Post findPost = postRepository.findById(postId)
            .orElseThrow(NotFoundPostException::new);
        validateWriter(member, findPost);
        postRepository.deleteById(findPost.getId());
    }

    private void validateWriter(Member member, Post findPost) {
        if (findPost.isNotWrittenBy(member)) {
            throw new InvalidWriterException();
        }
    }
}
