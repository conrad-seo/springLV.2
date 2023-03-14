package com.sparta.hanghaespringsecond.dto;

import com.sparta.hanghaespringsecond.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String postcomment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;
    public CommentResponseDto(Comment comment){
        id = comment.getId();
        postcomment = comment.getPostcomment();
        createdAt = comment.getPost().getCreatedAt();
        modifiedAt = comment.getPost().getModifiedAt();
        username = comment.getUser().getUsername();
    }
}
