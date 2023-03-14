package com.sparta.hanghaespringsecond.dto;

import com.sparta.hanghaespringsecond.entity.Comment;
import com.sparta.hanghaespringsecond.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    private String content;
    private String title;
    public PostResponseDto(Post post){
        content = post.getContent();
        title = post.getTitle();
    }
}
