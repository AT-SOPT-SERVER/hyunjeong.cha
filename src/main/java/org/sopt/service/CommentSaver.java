package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Comment;
import org.sopt.repository.CommentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentSaver {

    private final CommentRepository commentRepository;

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }
}
