package org.sopt.service.validator;

import org.sopt.repository.PostRepository;
import org.sopt.utils.TextUtil;

import static org.sopt.exception.CommonException.*;

public class TitleValidator {

    private final TextUtil textUtil = new TextUtil();

    public void titleValidate(String title, boolean isExistTitle){
        
        if (title.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_TITLE.getMessage());
        } else if (textUtil.lengthTitleWithEmoji(title)> 30) {
            throw new IllegalArgumentException(TOO_LONG_TITLE.getMessage());
        } else if (isExistTitle) {
            throw new IllegalArgumentException(DUPLICATE_TITLE.getMessage());
        }
    }
}
