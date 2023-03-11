package com.sparta.hanghaespringsecond.controller;

import com.sparta.hanghaespringsecond.dto.PostRequestDto;
import com.sparta.hanghaespringsecond.dto.PostResponseDto;
import com.sparta.hanghaespringsecond.entity.Post;
import com.sparta.hanghaespringsecond.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {   //메모컨트롤러

    private final PostService postService;

    //메인페이지를 반환하는 기능
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }


    @PostMapping("/api/post")       //데이터베이스에 값 저장
    public PostRequestDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.createPost(requestDto, request);
    }

    @GetMapping("/api/posts")        //저장된 값들 출력
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/api/post/{id}")   //선택한 게시글 조회
    public PostResponseDto choicePost(@PathVariable Long id) {
        return postService.choicePost(id);
    }

    @PutMapping("/api/post/{id}")   //선택한 게시글 수정
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostResponseDto responseDto, HttpServletRequest request){
        return postService.update(id, responseDto, request);
    }

    @DeleteMapping("/api/post/{id}")    //선택한 게시글 삭제
    public Long deletePost(@PathVariable Long id,HttpServletRequest request){
        return postService.deleteMemo(id, request);
    }

}