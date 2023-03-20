package com.sparta.hanghaespringsecond.repository;

import com.sparta.hanghaespringsecond.entity.PostCut;
import com.sparta.hanghaespringsecond.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CutRepository extends JpaRepository<PostCut, Long> {

    Optional<PostCut> findByIdAndUser(Long id, User user);
    void deleteByPostId(Long id);
}
