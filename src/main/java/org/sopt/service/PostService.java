package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.domain.enums.PostType;
import org.sopt.dto.*;
import org.sopt.exception.CustomException;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
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

    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
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
    public PostAllResponse getAllPosts() {
        List<PostListResponse> postResponses = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(PostListResponse::from)
                .toList();

        return PostAllResponse.from(postResponses);
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(EMPTY_POST));

        return PostResponse.from(post);
    }

    @Transactional
    public void deletePostById(Long id, Long userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(EMPTY_POST));

        if (!post.getUser().getId().equals(userId))
            throw new CustomException(USER_UNAUTHORIZED);

        postRepository.deleteById(id);
    }

    @Transactional
    public PostResponse updatePost(Long id, PostUpdateRequest request, Long userId){
        validateTitle(request.title());
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(EMPTY_POST));

        if (!post.getUser().getId().equals(userId))
            throw new CustomException(USER_UNAUTHORIZED);

        if (request.title() != null) {
            post.updateTitle(request.title());
        }

        if (request.content() != null) {
            post.updateContent(request.content());
        }

        return PostResponse.from(post);
    }

    @Transactional(readOnly = true)
    public PostAllResponse searchPostsByKeyword(String keyword){

        List<PostListResponse> postResponses = postRepository.findAll().stream()
                .map(PostListResponse::from)
                .toList();

        return PostAllResponse.from(postResponses);
    }

    @Transactional(readOnly = true)
    public PostAllResponse searchPostsByTag(String tag){

        List<PostListResponse> postResponses = postRepository.findAll().stream()
                .map(PostListResponse::from)
                .toList();

        return PostAllResponse.from(postResponses);
    }

    private void validateTitle(String title){
        if (postRepository.existsByTitle(title)) {
            throw new CustomException(DUPLICATE_TITLE);
        }
    }
}