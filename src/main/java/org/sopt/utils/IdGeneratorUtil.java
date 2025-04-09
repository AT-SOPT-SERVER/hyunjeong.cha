package org.sopt.utils;

public class IdGeneratorUtil {

    private static int postId = 1;
    public int generateId(){
        return postId++;
    }
}
