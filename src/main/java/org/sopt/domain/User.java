package org.sopt.domain;

import jakarta.persistence.*;

@Entity(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
