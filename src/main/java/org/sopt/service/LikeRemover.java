package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Like;
import org.sopt.repository.LikeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeRemover {

    private final LikeRepository likeRepository;

    public void delete(Like like){
        likeRepository.delete(like);
    }
}
