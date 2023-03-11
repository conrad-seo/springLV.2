package com.sparta.hanghaespringsecond.entity;

import com.sparta.hanghaespringsecond.dto.PostRequestDto;


import com.sparta.hanghaespringsecond.dto.PostResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private User user;

    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
    }

    public void update(PostResponseDto postResponseDto) {
        this.title = postResponseDto.getTitle();
        this.content = postResponseDto.getContent();
    }

}
