package org.sopt.domain;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    public Post(String title) {
        this.title = title;
    }

    public Post() {

    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

}