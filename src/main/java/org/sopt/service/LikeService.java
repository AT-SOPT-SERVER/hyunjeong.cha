package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Comment;
import org.sopt.domain.Like;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.domain.enums.LikeType;
import org.sopt.dto.ToggleLikeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserReader userReader;
    private final CommentReader commentFinder;
    private final LikeReader likeFinder;
    private final LikeRemover likeRemover;
    private final LikeSaver likeSaver;
    private final PostReader postReader;

    @Transactional
    public void toggleLike(Long memberId, ToggleLikeRequest request){
        User findUser = userReader.getById(memberId);
        toggle(request, findUser);
    }

    private void toggle(ToggleLikeRequest request, User user){

        if (LikeType.valueOf(request.likeType()) == LikeType.COMMENT){
            Comment findComment = commentFinder.getById(request.id());
            Optional<Like> likeOptional = likeFinder.findByUserAndComment(user, findComment);

            if (likeOptional.isPresent()) {
                likeRemover.delete(likeOptional.get());
            } else {
                Like newLike = Like.forComment(user, findComment);
                likeSaver.save(newLike);
            }
        } else {
            Post findPost = postReader.getById(request.id());

            Optional<Like> likeOptional = likeFinder.findByUserAndPost(user, findPost);

            if (likeOptional.isPresent()) {
                likeRemover.delete(likeOptional.get());
            } else {
                Like newLike = Like.forPost(user, findPost);
                likeSaver.save(newLike);
            }
        }

    }


}
