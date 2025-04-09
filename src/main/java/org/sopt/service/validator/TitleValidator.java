package org.sopt.service.validator;

import static org.sopt.exception.CommonException.EMPTY_TITLE;
import static org.sopt.exception.CommonException.TOO_LONG_TITLE;

public class TitleValidator {

    public void titleValidate(String title){
        
        if (title.isEmpty())
            throw new IllegalArgumentException(EMPTY_TITLE.getMessage());
        else if (title.length() > 30) {
            throw new IllegalArgumentException(TOO_LONG_TITLE.getMessage());
        }
    }
}
