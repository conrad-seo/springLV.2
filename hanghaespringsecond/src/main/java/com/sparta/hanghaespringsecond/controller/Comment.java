package com.sparta.hanghaespringsecond.controller;

import com.sparta.hanghaespringsecond.dto.CommentRequestDto;
import com.sparta.hanghaespringsecond.dto.CommentResponseDto;
import com.sparta.hanghaespringsecond.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/com")
public class Comment {

    private final CommentService commentService;

    @ResponseBody       //Spring MVC에서 HTTP 응답 본문을 작성하는 데 사용//@ResponseBody 어노테이션을 사용하여 JSON 형식으로 데이터를 반환할 수 있슴
    @PostMapping("/{id}")
    public CommentResponseDto GetComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request){
        return commentService.getcomment(id, commentRequestDto, request);
        //@RequestBody -  HTTP 요청의 body 부분에 담긴 데이터를 자바 객체로 변환해주는 역할, 보통 POST나 PUT 요청과 함께 사용
    }

    @ResponseBody
    @PutMapping("/{id}/{comid}")
    public CommentResponseDto UpdateComment(@PathVariable long id, @PathVariable long comid, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request){
        return commentService.updatecomment(id, comid, commentRequestDto, request);
    }

    @ResponseBody
    @DeleteMapping("/{id}/{comid}")
    public CommentResponseDto DeleteComment(@PathVariable long id, @PathVariable long comid, HttpServletRequest request){
        return commentService.deleteComment(id, comid, request);
    }



}
