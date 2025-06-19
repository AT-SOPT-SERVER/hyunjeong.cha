package org.sopt.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public User(String name){
        this.name = name;
    }

    public User() {
    }

    public String getName(){
        return this.name;
    }

    public Long getId(){
        return this.id;
    }

}
