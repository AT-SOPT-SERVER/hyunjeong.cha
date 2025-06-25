package org.sopt.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.Post;
import org.springframework.stereotype.Repository;

import static org.sopt.domain.QPost.post;
import static org.sopt.domain.QUser.user;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> searchByTitleOrUserName(String keyword) {
        return jpaQueryFactory.selectFrom(post)
                .join(post.user, user)
                .where(
                        post.title.contains(keyword)
                                .or(user.name.eq(keyword))
                )
                .fetch();
    }
}