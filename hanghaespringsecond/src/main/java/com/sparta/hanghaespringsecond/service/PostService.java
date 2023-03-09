package com.sparta.hanghaespringsecond.service;

import com.sparta.hanghaespringsecond.dto.PostRequestDto;
import com.sparta.hanghaespringsecond.dto.PostResponseDto;
import com.sparta.hanghaespringsecond.entity.Post;
import com.sparta.hanghaespringsecond.entity.User;
import com.sparta.hanghaespringsecond.jwt.JwtUtil;
import com.sparta.hanghaespringsecond.repository.PostRepository;
import com.sparta.hanghaespringsecond.repository.UserRepository;
import io.jsonwebtoken.Claims;
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
    private final UserRepository userRepository;

    @Transactional      //저장
    public PostRequestDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;      //jwt안에 들어있는 정보들을 담을 수 있는 객체
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Post post = postRepository.saveAndFlush(new Post(requestDto, user.getId()));

            return new PostRequestDto(post);
        } else {
            return null;
        }
    }

    @Transactional      //출력
    public List<PostResponseDto> getPosts() {
        List<PostResponseDto> list = postRepository.findAllByOrderByModifiedAtDesc();
        return list;
    }

    @Transactional      //수정및 출력
    public PostResponseDto update(Long id, PostResponseDto postResponseDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;      //jwt안에 들어있는 정보들을 담을 수 있는 객체

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            // 게시글이 존재하는지 확인
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

            // 해당 사용자가 작성한 게시글인지 확인
            if (!user.getId().equals(post.getUserId())) {
                throw new IllegalArgumentException("사용자가 작성한 게시글이 아닙니다");
            }

            post.update(postResponseDto);

        } else {
            return null;
        }
        return null;
    }

//        Post post = checkPost(id);
//        if(post.getPassword().equals(postRequestDto.getPassword())) {
//            post.update(postRequestDto);
//        }
//        else{
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
//        }
//
//        return post.getId();

    @Transactional      //삭제
    public Long deleteMemo(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 게시글이 존재하는지 확인
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

            //해당 사용자가 작성한 게시글인지 확인
            if (!user.getId().equals(post.getUserId())) {
                throw new IllegalArgumentException("사용자가 작성한 게시글이 아닙니다");
            }

            postRepository.delete(post);
        }
        return null;
    }

    @Transactional      //지정 출력
    public Post choicePost(Long id) {
        Post post = checkPost(id);
        return post;
    }

    private Post checkPost(Long id) {        //중복코드
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
    }
}           //PostResponseDto로 바꾸기, 연관관계 db관계 하나가 다수를 찾그거 보다 다수가 하나를 찾는게 훨씬 빠르다
        //책     가격


