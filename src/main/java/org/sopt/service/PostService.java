package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.PostAllResponse;
import org.sopt.dto.PostRequest;
import org.sopt.dto.PostIdResponse;
import org.sopt.dto.PostResponse;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.sopt.exception.CommonException.DUPLICATE_TITLE;
import static org.sopt.exception.CommonException.EMPTY_POST;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
   }

   @Transactional
    public PostIdResponse createPost(PostRequest request){
        if (postRepository.existsByTitle(request.title())) {
            throw new IllegalArgumentException(DUPLICATE_TITLE.getMessage());
        }
        Post post = new Post(request.title());

        return new PostIdResponse(postRepository.save(post).getId());
    }

    @Transactional(readOnly = true)
    public PostAllResponse getAllPosts() {
        List<PostResponse> postResponses = postRepository.findAll().stream()
                .map(post -> new PostResponse(post.getTitle(), post.getId()))
                .toList();

        return new PostAllResponse(postResponses);
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(EMPTY_POST.getMessage()));

        return new PostResponse(post.getTitle(), post.getId());
    }

    @Transactional
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public PostResponse updatePost(Long id, PostRequest request){
        if (postRepository.existsByTitle(request.title())) {
            throw new IllegalArgumentException(DUPLICATE_TITLE.getMessage());
        }
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NullPointerException(EMPTY_POST.getMessage()));
        post.updateTitle(request.title());
        return new PostResponse(post.getTitle(), post.getId());
    }

    @Transactional(readOnly = true)
    public PostAllResponse searchPostsByKeyword(String keyword){

        List<PostResponse> postResponses = postRepository.findByTitleContaining(keyword).stream()
                .map(post -> new PostResponse(post.getTitle(), post.getId()))
                .toList();

        return new PostAllResponse(postResponses);
    }


}