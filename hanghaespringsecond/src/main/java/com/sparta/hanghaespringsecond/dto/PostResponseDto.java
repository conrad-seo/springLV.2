package com.sparta.hanghaespringsecond.dto;

import com.sparta.hanghaespringsecond.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    private String content;
    private String title;
    public PostResponseDto(Post post){
        this.content = post.getContent();
        this.title = post.getTitle();
    }
}
