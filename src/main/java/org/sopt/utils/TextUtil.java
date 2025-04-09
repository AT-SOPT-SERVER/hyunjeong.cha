package org.sopt.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
    private static final Pattern graphemePattern = Pattern.compile("\\X");

    public int lengthTitleWithEmoji(String text) {
        if (text == null) return 0;

        Matcher matcher = graphemePattern.matcher(text);
        int count = 0;

        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
