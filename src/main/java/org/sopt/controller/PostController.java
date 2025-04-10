package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.io.IOException;
import java.util.List;

public class PostController {
    private final PostService postService = new PostService();

    public void createPost(final String title) {
        try {
            postService.createPost(title);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(final Long id) {
        return postService.getPostById(id);
    }
    public Boolean updatePostTitle(final Long id, final String newTitle) {
        try {
            return postService.updatePost(id, newTitle);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deletePostById(final Long id) {
        return postService.deletePostById(id);
    }

    public List<Post> searchPostsByKeyword(final String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }

    public void loadFile() {
        try {
            postService.loadFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}