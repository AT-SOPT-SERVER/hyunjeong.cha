package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.domain.common.BaseTimeEntity;
import org.sopt.domain.enums.PostType;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "like", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();

    public Post(String title, String content, User user, PostType postType) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.postType = postType;
    }

    public Post() {

    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updatePost(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public User getUser() {
        return user;
    }
}