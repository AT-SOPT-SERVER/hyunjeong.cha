package org.sopt.utils;

import org.sopt.common.ErrorCode;
import org.sopt.common.PostErrorCode;
import org.sopt.exception.CustomException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.sopt.common.UserErrorCode.TOO_LONG_NAME;

public class TextUtil {
    private static final Pattern graphemePattern = Pattern.compile("\\X");

    public static void validatePost(String title, String content) {
        validateLengthWithEmoji(title, 30, PostErrorCode.TOO_LONG_TITLE);
        validateLengthWithEmoji(content, 100, PostErrorCode.TOO_LONG_CONTENT);
    }

    public static void validateName(String name) {
        validateLengthWithEmoji(name, 10, TOO_LONG_NAME);
    }

    private static void validateLengthWithEmoji(String text, int limit, ErrorCode errorCode) {
        if (text == null) return;

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
