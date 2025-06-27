package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostCustomRepository {

    List<Post> searchByTitleOrUserName(String keyworde);

    List<Post> searchByTag(String tag);
}
