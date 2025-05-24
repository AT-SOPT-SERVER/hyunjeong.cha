package org.sopt.repository;

import org.sopt.domain.Post;
import org.sopt.domain.enums.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.user.name LIKE %:keyword%")
    List<Post> searchByTitleOrUserName(@Param("keyword") String keyword);

    List<Post> findByPostType(PostType postType);
}