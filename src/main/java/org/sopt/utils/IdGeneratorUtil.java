package org.sopt.utils;

public class IdGeneratorUtil {

    private static Long postId = 1L;
    public Long generateId(){
        return postId++;
    }
}
