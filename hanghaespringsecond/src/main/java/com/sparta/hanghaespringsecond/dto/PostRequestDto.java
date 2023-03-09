package com.sparta.hanghaespringsecond.dto;

import com.sparta.hanghaespringsecond.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {           //db??에서 받아온 값을 예쁘게 감싸기 //클아이언트에서 받아온 값을 예쁘게 받는것
    private String title;
    private String username;
    private String content;
    public PostRequestDto(Post post) {
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.content = post.getContent();
    }

}
