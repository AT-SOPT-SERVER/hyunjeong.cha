package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Comment;
import org.sopt.exception.CustomException;
import org.sopt.repository.CommentRepository;
import org.springframework.stereotype.Component;

import static org.sopt.common.CommentErrorCode.COMMENT_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class CommentReader {

    private final CommentRepository commentRepository;

    public Comment getById(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));
    }
}
