package com.sparta.hanghaespringsecond.repository;

import com.sparta.hanghaespringsecond.dto.PostResponseDto;
import com.sparta.hanghaespringsecond.entity.Post;
import com.sparta.hanghaespringsecond.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> { //db에서 꺼내거나 넣기
    List<PostResponseDto> findAllByOrderByModifiedAtDesc();

    Optional<Post> findByIdAndUser(Long id, User user);

    List<Post> findAllByUserId(Long userId);    //유저 아이디랑 똑같은 모든 게시글을 가지고 온다
    List<Post> findByIdAndUserId(Long id, Long userId);     //post아이디와 유저 아이디가일치하는 post을 가지 온다
}

