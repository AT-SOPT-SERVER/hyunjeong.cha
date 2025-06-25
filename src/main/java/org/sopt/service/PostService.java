package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.*;
import org.sopt.dto.*;
import org.sopt.exception.CustomException;
import org.sopt.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.sopt.common.PostErrorCode.*;
import static org.sopt.common.UserErrorCode.USER_UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentReader commentReader;
    private final UserReader userReader;
    private final PostReader postReader;
    private final TagReader tagReader;

    @Transactional
    public PostIdResponse createPost(PostRequest request, Long userId){
       validateTitle(request.title());

       User user = userReader.getById(userId);

       Post post = new Post(request.title(), request.content(), user);

        for (String tagName : request.postType()) {
            Tag tag = tagReader.findByName(tagName);
            PostTag.createPostTag(post, tag);
        }

        return PostIdResponse.from(postRepository.save(post));
    }

    @Transactional(readOnly = true)
    public PostAllResponse getAllPosts(int size, int page) {
        Pageable pageable = PageRequest.of(size, page);
        Page<Post> postPage = postRepository.findAllByOrderByCreatedAtDesc(pageable);

        return PostAllResponse.from(postPage);
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));

        List<CommentResponse> comments = commentReader.getAllByPost(post)
                .stream().map(comment -> CommentResponse.of(comment)).toList();

        return PostResponse.from(post, comments);
    }

    @Transactional
    public void deletePostById(Long id, Long userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));

        if (!post.getUser().getId().equals(userId))
            throw new CustomException(USER_UNAUTHORIZED);

        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(Long id, PostUpdateRequest request, Long userId){
        validateTitle(request.title());
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));

        if (!post.getUser().getId().equals(userId))
            throw new CustomException(USER_UNAUTHORIZED);

        post.updateTitle(request.title());
        post.updateContent(request.content());
    }

    @Transactional(readOnly = true)
    public PostSearchResponse searchPostsByKeyword(String keyword){

        List<PostListResponse> postResponses = postReader.searchByTitleOrUserName(keyword).stream()
                .map(PostListResponse::from)
                .toList();

        return PostSearchResponse.from(postResponses);
    }

    @Transactional(readOnly = true)
    public PostSearchResponse searchPostsByTag(String tagName){

        List<PostListResponse> postResponses = postReader.searchByTag(tagName).stream()
                .map(PostListResponse::from)
                .toList();

        return PostSearchResponse.from(postResponses);
    }

    private void validateTitle(String title){
        if (postRepository.existsByTitle(title)) {
            throw new CustomException(DUPLICATE_TITLE);
        }
    }
}