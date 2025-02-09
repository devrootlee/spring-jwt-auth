package com.spring.jwt.auth.api.service;

import com.spring.jwt.auth.api.common.security.JwtProvider;
import com.spring.jwt.auth.api.dto.MemberDto;
import com.spring.jwt.auth.api.entity.Member;
import com.spring.jwt.auth.api.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    JwtProvider jwtProvider;

    MemberRepository memberRepository;

    public MemberService(JwtProvider jwtProvider, MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.jwtProvider = jwtProvider;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map signUp(MemberDto.signUpDto signUpDto) throws Exception {
        Map result = new HashMap();

        Member member = Member.builder()
                .userId(signUpDto.getUserId())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .name(signUpDto.getName())
                .idType(signUpDto.getIdType())
                .idValue(passwordEncoder.encode(signUpDto.getIdValue()))
                .build();


        //userId 중복체크
        if (memberRepository.existsById(signUpDto.getUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아이디가 이미 존재합니다.");
        } else {
            memberRepository.save(member);
        }

        return result;
    }

    public Map logIn(MemberDto.loginDto loginDto) throws Exception {
        Map result = new HashMap();

        //아이디가 존재할 경우
        if (memberRepository.findById(loginDto.getUserId()).isPresent()) {
            Member member = memberRepository.findById(loginDto.getUserId()).get();

            //비밀번호가 일치할 경우
            if (passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
                //jwt 생성
                String jwt = jwtProvider.generateJwt(loginDto.getUserId());
                result.put("token",jwt);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

        } else {//아이디가 존재하지 않을 경우
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return result;
    }

}
