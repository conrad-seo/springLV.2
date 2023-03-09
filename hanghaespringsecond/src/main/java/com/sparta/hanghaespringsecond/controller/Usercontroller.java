package com.sparta.hanghaespringsecond.controller;

import com.sparta.hanghaespringsecond.dto.LoginRequestDto;
import com.sparta.hanghaespringsecond.dto.SignupRequestDto;
import com.sparta.hanghaespringsecond.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class Usercontroller {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    //로그인
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }
}
