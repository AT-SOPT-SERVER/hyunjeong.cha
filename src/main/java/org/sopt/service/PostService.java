package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.service.validator.CreatedAtValidator;
import org.sopt.service.validator.TitleValidator;
import org.sopt.utils.IdGeneratorUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private final TitleValidator titleValidator = new TitleValidator();
    private final CreatedAtValidator createdAtValidator = new CreatedAtValidator();
    private final PostResolver postResolver = new PostResolver();
    private final IdGeneratorUtil idGenerator = new IdGeneratorUtil();

    private LocalDateTime createdAt;

    public void createPost(String title) {
        createdAtValidator.createdAtValidate(createdAt);
        titleValidator.titleValidate(title, postRepository.findTitle(title));
        int newPostId = idGenerator.generateId();
        Post post = new Post(newPostId, title);

        postRepository.save(post);
        createdAt = LocalDateTime.now();
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
        titleValidator.titleValidate(title, postRepository.findTitle(title));
        Post post = postResolver.resolvePost(postRepository.findPostById(id));
        post.update(title);
        return true;
    }

    public List<Post> searchPostsByKeyword(String keyword){
        return postRepository.searchPostsByKeyword(keyword);
    }
}