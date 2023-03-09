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
    public PostRequestDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request){//넘어오는 값이 객체 형식으로 넘오오기 때문에 @RequestBody를 사용
        //POST방식이기 때문에 body가 존재하고 그 body안에 우리가 저장 해야 되는 것들이 넘어오기 때문에 @RequestBody를 사용
        //MemoRequestDto 객체로 받는다 Dto객체를 만들지 않았기 때문에 MemoRequestDto를 개발을 하러 간다
        return postService.createPost(requestDto, request);
    }

    @GetMapping("/api/posts")        //저장된 값들 출력
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/api/post/{id}")   //선택한 게시글 조회
    public Post choicePost(@PathVariable Long id) {
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