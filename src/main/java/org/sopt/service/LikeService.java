package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Comment;
import org.sopt.domain.Like;
import org.sopt.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserReader userReader;
    private final CommentFinder commentFinder;
    private final LikeFinder likeFinder;
    private final LikeRemover likeRemover;

    @Transactional
    public void toggleCommentLike(Long memberId, Long commentId){
        User findUser = userReader.getById(memberId);
        Comment findComment = commentFinder.getById(commentId);


    }

    private boolean toggleComment(Comment comment, User user){
        Optional<Like> likeOptional = likeFinder.findByUserAndComment(user, comment);

        if (likeOptional.isPresent()) {
            likeRemover.delete(likeOptional.get());
        } else {
            likeSa
        }
    }



}
