package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Comment;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.CommentIdResponse;
import org.sopt.dto.CommentRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostReader postReader;
    private final UserReader userReader;
    private final CommentSaver commentSaver;

    @Transactional
    public CommentIdResponse createComment(CommentRequest request, Long userId, Long postId){

        User findUser = userReader.getById(userId);
        Post findPost = postReader.getById(postId);

        Comment newComment = Comment.createComment(request.content(), findUser, findPost);
        Long newCommentId = commentSaver.save(newComment).getId();
        return CommentIdResponse.of(newCommentId);
    }

}
