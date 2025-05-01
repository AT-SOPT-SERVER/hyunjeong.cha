package org.sopt.utils;

import org.sopt.common.PostErrorCode;
import org.sopt.exception.CustomException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
    private static final Pattern graphemePattern = Pattern.compile("\\X");

    public static void lengthTitleWithEmoji(String text) {
        Matcher matcher = graphemePattern.matcher(text);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        if (count > 30)
            throw new CustomException(PostErrorCode.TOO_LONG_TITLE);
    }
}
