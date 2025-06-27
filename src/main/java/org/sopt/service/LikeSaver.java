package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Like;
import org.sopt.repository.LikeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeSaver {
    private final LikeRepository likeRepository;

    public void save(Like like){
        likeRepository.save(like);
    }
}
