package org.sopt.domain;

public class Post {
    private Long id;
    private String title;

    public Post(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void update(String title) {
        this.title = title;
    }
}