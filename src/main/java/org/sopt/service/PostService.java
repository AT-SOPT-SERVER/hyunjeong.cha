package org.sopt.service;

import org.sopt.domain.Comment;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.domain.enums.PostType;
import org.sopt.dto.*;
import org.sopt.exception.CustomException;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.sopt.common.PostErrorCode.*;
import static org.sopt.common.UserErrorCode.USER_NOT_FOUND;
import static org.sopt.common.UserErrorCode.USER_UNAUTHORIZED;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentReader commentReader;

    public PostService(PostRepository postRepository, UserRepository userRepository, CommentReader commentReader){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentReader = commentReader;
    }

   @Transactional
    public PostIdResponse createPost(PostRequest request, Long userId){
       validateTitle(request.title());

       User user = userRepository.findById(userId)
               .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        Post post = new Post(request.title(), request.content(), user, PostType.valueOf(request.postType()));

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

        if (request.title() != null) {
            post.updateTitle(request.title());
        }

        if (request.content() != null) {
            post.updateContent(request.content());
        }

    }

    @Transactional(readOnly = true)
    public PostSearchResponse searchPostsByKeyword(String keyword){

        List<PostListResponse> postResponses = postRepository.findAll().stream()
                .map(PostListResponse::from)
                .toList();

        return PostSearchResponse.from(postResponses);
    }

    @Transactional(readOnly = true)
    public PostSearchResponse searchPostsByTag(String tag){

        List<PostListResponse> postResponses = postRepository.findAll().stream()
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