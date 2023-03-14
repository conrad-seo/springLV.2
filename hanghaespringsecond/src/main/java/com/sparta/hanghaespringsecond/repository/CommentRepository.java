package com.sparta.hanghaespringsecond.repository;

import com.sparta.hanghaespringsecond.entity.Comment;
import com.sparta.hanghaespringsecond.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndPost(Long id, Post post);
    Optional<Comment> findById(long id);

    void deleteById(Long id);
}
