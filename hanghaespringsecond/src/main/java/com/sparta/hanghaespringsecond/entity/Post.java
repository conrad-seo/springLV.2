package com.sparta.hanghaespringsecond.entity;

import com.sparta.hanghaespringsecond.dto.PostRequestDto;


import com.sparta.hanghaespringsecond.dto.PostResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long userId;        //

    public Post(PostRequestDto requestDto, Long userId) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.userId = userId;
    }

    public void update(PostResponseDto postResponseDto) {
        this.title = postResponseDto.getTitle();
        this.content = postResponseDto.getContent();
    }
}
