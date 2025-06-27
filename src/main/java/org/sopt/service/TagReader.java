package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.common.TagErrorCode;
import org.sopt.domain.Post;
import org.sopt.domain.Tag;
import org.sopt.exception.CustomException;
import org.sopt.repository.TagRepository;
import org.springframework.stereotype.Component;

import static org.sopt.common.TagErrorCode.TAG_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class TagReader {

    private final TagRepository tagRepository;

    public Tag findByName(String postType){
        return tagRepository.findByName(postType)
                .orElseThrow(() -> new CustomException(TAG_NOT_FOUND));
    }
}
