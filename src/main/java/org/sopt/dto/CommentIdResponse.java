package org.sopt.dto;

public record CommentIdResponse(
        Long id
) {
    public static CommentIdResponse of(Long id){
        return new CommentIdResponse(id);
    }
}
