package org.sopt.utils;

public class IdGeneratorUtil {

    private static Long postId = 1L;
    public static Long generateId(){
        return postId++;
    }
}
