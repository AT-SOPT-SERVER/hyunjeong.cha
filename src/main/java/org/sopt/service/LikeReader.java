package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Comment;
import org.sopt.domain.Like;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.repository.LikeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LikeReader {

    private final LikeRepository likeRepository;

    public Optional<Like> findByUserAndComment(User user, Comment comment){
        return likeRepository.findByUserAndComment(user, comment);
    }

    public Optional<Like> findByUserAndPost(User user, Post post){
        return likeRepository.findByUserAndPost(user, post);
    }

}
