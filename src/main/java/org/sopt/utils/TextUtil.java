package org.sopt.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
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
