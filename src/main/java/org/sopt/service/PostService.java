package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.service.validator.TitleValidator;

import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private final TitleValidator titleValidator = new TitleValidator();
    private final PostResolver postResolver = new PostResolver();
    private int postId = 1;

    public void createPost(String title) {
        Post post = new Post(postId++, title);

        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.delete(id);
    }

    public boolean updatePost(int id, String title){
        titleValidator.titleValidate(title);
        Post post = postResolver.resolvePost(postRepository.findPostById(id));
        post.update(title);
        return true;
    }
}