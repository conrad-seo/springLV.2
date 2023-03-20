package com.sparta.hanghaespringsecond.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PostCut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reply;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    public PostCut(String reply, User user, Post post) {
        this.reply = reply;
        this.user = user;
        this.post = post;
    }

}
