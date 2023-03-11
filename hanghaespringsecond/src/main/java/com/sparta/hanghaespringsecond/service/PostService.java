package com.sparta.hanghaespringsecond.service;

import com.sparta.hanghaespringsecond.dto.PostRequestDto;
import com.sparta.hanghaespringsecond.dto.PostResponseDto;
import com.sparta.hanghaespringsecond.entity.Post;
import com.sparta.hanghaespringsecond.entity.User;
import com.sparta.hanghaespringsecond.entity.UserRoleEnum;
import com.sparta.hanghaespringsecond.jwt.JwtUtil;
import com.sparta.hanghaespringsecond.repository.CutRepository;
import com.sparta.hanghaespringsecond.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;    //MemoRepository에 연결이 되어서 사용이 가능하다
    private final JwtUtil jwtUtil;
    private final CutRepository cutRepository;

    @Transactional      //저장
    public PostRequestDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        Post post = postRepository.saveAndFlush(new Post(requestDto, user));
        return new PostRequestDto(post);
    }

    @Transactional      //출력
    public List<PostResponseDto> getPosts() {
        List<PostResponseDto> list = postRepository.findAllByOrderByModifiedAtDesc();
        return list;
    }

    @Transactional      //수정및 출력
    public PostResponseDto update(Long id, PostResponseDto postResponseDto, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        Post post = getPost(id);

        checkPostRole(id, user);

        post.update(postResponseDto);
        return new PostResponseDto(post);
    }


    @Transactional      //삭제
    public Long deleteMemo(Long id, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);

        checkPostRole(id, user);

        cutRepository.deleteByPostId(id);     //게시글 삭제
        postRepository.deleteById(id);

        return null;
    }

    @Transactional      //지정 출력
    public PostResponseDto choicePost(Long id) {
        Post post = getPost(id);
        return new PostResponseDto(post);
    }

    private Post checkPost(Long id) {        //중복코드
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
    }

        private Post getPost(Long id) {
            return postRepository.findById(id).orElseThrow(     //쓸데없이 착해
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
            );
        }

    private void checkPostRole(Long id, User user) {
        if (user.getRole() == UserRoleEnum.ADMIN) return;
        postRepository.findByIdAndUser(id, user).orElseThrow(
                () -> new IllegalArgumentException("권한이 없습니다.")
        );
    }
}
