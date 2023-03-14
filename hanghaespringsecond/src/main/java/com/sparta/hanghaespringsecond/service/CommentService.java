package com.sparta.hanghaespringsecond.service;

import com.sparta.hanghaespringsecond.dto.CommentRequestDto;
import com.sparta.hanghaespringsecond.dto.CommentResponseDto;
import com.sparta.hanghaespringsecond.entity.Comment;
import com.sparta.hanghaespringsecond.entity.Post;
import com.sparta.hanghaespringsecond.entity.User;
import com.sparta.hanghaespringsecond.entity.UserRoleEnum;
import com.sparta.hanghaespringsecond.jwt.JwtUtil;
import com.sparta.hanghaespringsecond.repository.CommentRepository;
import com.sparta.hanghaespringsecond.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final JwtUtil jwtUtil;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    //댓글 작성
    @Transactional
    public CommentResponseDto getcomment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest request){
        User user = jwtUtil.getUser(request);
        Post post = getPost(id);
        Comment comment = commentRepository.saveAndFlush(new Comment(commentRequestDto, user, post));
        return new CommentResponseDto(comment);
    }
    @Transactional  //메소드 또는 클래스에 트랜잭션 기능을 부여하는 역할//이 어노테이션이 부여된 메소드나 클래스 내에서 일어나는 모든 DB 작업은 트랜잭션으로 묶이게 됨
    public CommentResponseDto updatecomment(Long id, Long comid, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        Post post = getPost(id);
        Comment comment = getComment(comid);

        checkCommentRole(comid, post, user);

        comment.updatecom(commentRequestDto);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto deleteComment(long id, long comid, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        Post post = getPost(id);

        checkCommentRole(comid, post, user);

        commentRepository.deleteById(comid);
        return null;
    }



    private Post getPost(long id){
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
    }

    private Comment getComment(long comid){
        return commentRepository.findById(comid).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
    }

    private void checkCommentRole(long comid, Post post, User user){
        if(user.getRole() == UserRoleEnum.ADMIN) return;
        commentRepository.findByIdAndPost(comid, post).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
    }
}
