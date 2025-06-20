package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.exception.CustomException;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Component;

import static org.sopt.common.PostErrorCode.POST_NOT_FOUND;
import static org.sopt.common.UserErrorCode.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class PostReader {

    private final PostRepository postRepository;

    public Post getById(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }
}
