package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.exception.CustomException;
import org.sopt.repository.PostCustomRepository;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.sopt.common.PostErrorCode.POST_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class PostReader {

    private final PostRepository postRepository;
    private final PostCustomRepository postCustomRepository;

    public Post getById(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }

    public List<Post> searchByTitleOrUserName(String keyword){
        return postCustomRepository.searchByTitleOrUserName(keyword);
    }
}
