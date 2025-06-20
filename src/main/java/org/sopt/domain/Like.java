package org.sopt.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.sopt.domain.enums.LikeType;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LikeType likeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private Like(User user, Post post, LikeType likeType) {
        this.user = user;
        this.post = post;
        this.likeType = likeType;
    }

    private Like(User user, Comment comment, LikeType likeType) {
        this.user = user;
        this.comment = comment;
        this.likeType = likeType;
    }

    public static Like forComment(User user, Comment comment){
        return new Like(user, comment, LikeType.COMMENT);
    }

    public static Like forPost(User user, Post post){
        return new Like(user, post, LikeType.POST);
    }


}
