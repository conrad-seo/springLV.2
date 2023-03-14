package com.sparta.hanghaespringsecond.entity;

import com.sparta.hanghaespringsecond.dto.CommentRequestDto;
import com.sparta.hanghaespringsecond.dto.CommentResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String postcomment;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "postid", nullable = false)
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, User user, Post post){
        this.postcomment = commentRequestDto.getPostcomment();
        this.user = user;
        this.post = post;
    }

    public void updatecom(CommentRequestDto commentRequestDto){
        postcomment = commentRequestDto.getPostcomment();
    }
}
