package org.sopt.service;

import org.sopt.domain.Post;

import static org.sopt.exception.CommonException.EMPTY_POST;

public class PostResolver {

    public Post resolvePost(Post post){
        if (post == null)
            throw new NullPointerException(EMPTY_POST.getMessage());

        return post;
    }
}
