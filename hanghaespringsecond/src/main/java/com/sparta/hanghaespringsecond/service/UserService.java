package com.sparta.hanghaespringsecond.service;

import com.sparta.hanghaespringsecond.dto.LoginRequestDto;
import com.sparta.hanghaespringsecond.dto.SignupRequestDto;
import com.sparta.hanghaespringsecond.entity.User;
import com.sparta.hanghaespringsecond.jwt.JwtUtil;
import com.sparta.hanghaespringsecond.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public String signup(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        if (username == null) {
            return "username이 null입니다";
        }

        //아이디 중복인지 확인하는 코드
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        //문자열 길이 체크
        if(username.length()<4 || username.length()>10){
            return "아이디 길이가 4이상 10이하여야 합니다";
        }

        //문자열을 문자 배열로 변환후 소문자랑 숫자인지 확인
        for(char ch : username.toCharArray()){
            if (!Character.isLowerCase(ch) && !Character.isDigit(ch)) {
                return ("소문자와 숫자만 입력해 주시길 바랍니다");
            }
        }

        //비밀번호 길이
        if(password.length()<8 || password.length()>15){
            return "비밀번호 길이는 8~15 이내로 작성해 주시길 바랍니다";
        }
        for(char ch : password.toCharArray()){
            if (!Character.isLowerCase(ch) && !Character.isDigit(ch)&&!Character.isUpperCase(ch)) {
                return ("대문자, 소문자그리고 숫자만 입력해 주시길 바랍니다");
            }
        }

        User user = new User(username, password);
        userRepository.save(user);

        System.out.println(user);
        return "회원 가입 성공";
    }

    public String login(SignupRequestDto signupRequestDto){

        return null;
    }


    @Transactional()
    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
        System.out.println("토큰 저장 성공");
        return "hehhhhhhhhhhhhhh";
    }
}
