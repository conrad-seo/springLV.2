package com.sparta.hanghaespringsecond.controller;

import com.sparta.hanghaespringsecond.dto.LoginRequestDto;
import com.sparta.hanghaespringsecond.dto.SignupRequestDto;
import com.sparta.hanghaespringsecond.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class Usercontroller {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup/user")
    public String signupuser(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signupuser(signupRequestDto);
    }

    @PostMapping("/signup/admin")
    public String signupadmin(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signupadmin(signupRequestDto);
    }

    //로그인
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }
}
