package org.sopt.utils;

import org.sopt.common.PostErrorCode;
import org.sopt.exception.CustomException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
    private static final Pattern graphemePattern = Pattern.compile("\\X");

    public static void validatePost(String title, String content) {
        validateLengthWithEmoji(title, 30, PostErrorCode.TOO_LONG_TITLE);
        validateLengthWithEmoji(content, 100, PostErrorCode.TOO_LONG_CONTENT);
    }

    public static void validateName(String name) {
        validateLengthWithEmoji(name, 10, PostErrorCode.TOO_LONG_NAME);
    }

    private static void validateLengthWithEmoji(String text, int limit, PostErrorCode errorCode) {
        if (text == null) return;  // null-safe 처리

        Matcher matcher = graphemePattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        if (count > limit) {
            throw new CustomException(errorCode);
        }
    }
}
