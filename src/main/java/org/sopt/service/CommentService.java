package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Comment;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.CommentIdResponse;
import org.sopt.dto.CommentRequest;
import org.sopt.exception.CustomException;
import org.sopt.repository.CommentRepository;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.sopt.common.PostErrorCode.POST_NOT_FOUND;
import static org.sopt.common.UserErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentIdResponse createComment(CommentRequest request, Long userId, Long postId){

        User findUser = validateUser(userId);
        Post findPost = validatePost(postId);

        Comment newComment = Comment.createComment(request.content(), findUser, findPost);
        Long newCommentId = commentRepository.save(newComment).getId();
        return CommentIdResponse.of(newCommentId);
    }

    private User validateUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }

    private Post validatePost(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }
}
