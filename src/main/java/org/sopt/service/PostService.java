package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.service.validator.TitleValidator;
import org.sopt.utils.IdGeneratorUtil;

import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private final TitleValidator titleValidator = new TitleValidator();
    private final PostResolver postResolver = new PostResolver();
    private final IdGeneratorUtil idGenerator = new IdGeneratorUtil();

    public void createPost(String title) {
        titleValidator.titleValidate(title, postRepository.findTitle(title));
        int newPostId = idGenerator.generateId();
        Post post = new Post(newPostId, title);

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
        titleValidator.titleValidate(title, postRepository.findTitle(title));
        Post post = postResolver.resolvePost(postRepository.findPostById(id));
        post.update(title);
        return true;
    }
}