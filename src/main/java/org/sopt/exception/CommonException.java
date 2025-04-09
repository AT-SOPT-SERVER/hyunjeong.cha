package org.sopt.exception;

public enum CommonException {

    EMPTY_TITLE("제목이 비어있을 수 없습니다."),
    TOO_LONG_TITLE("제목은 30자 미만이어야 합니다."),
    EMPTY_POST("해당 게시글이 존재하지 않습니다."),
    DUPLICATE_TITLE("같은 제목의 게시글은 작성할 수 없습니다."),
    CREATE_DURATION("새로운 게시글은 3분 뒤에 작성할 수 있습니다.");

    private final String message;

    CommonException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
