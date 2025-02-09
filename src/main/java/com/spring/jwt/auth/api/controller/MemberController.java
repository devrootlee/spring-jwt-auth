package com.spring.jwt.auth.api.controller;

import com.spring.jwt.auth.api.common.util.CommonUtil;
import com.spring.jwt.auth.api.dto.MemberDto;
import com.spring.jwt.auth.api.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MemberController {

    CommonUtil commonUtil;

    MemberService memberService;

    public MemberController(CommonUtil commonUtil, MemberService memberService) {
        this.commonUtil = commonUtil;
        this.memberService = memberService;
    }

    /**
     * 회원가입
     * @param signUpDto
     * @return
     * @throws Exception
     */
    @PostMapping("/signUp")
    public ResponseEntity signUp(@Valid @RequestBody MemberDto.signUpDto signUpDto) throws Exception {

        return commonUtil.successResponseUtil(memberService.signUp(signUpDto), HttpStatus.OK);
    }

    /**
     * 로그인
     * @param loginDto
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity logIn(@Valid @RequestBody MemberDto.loginDto loginDto) throws Exception {

        return commonUtil.successResponseUtil(memberService.logIn(loginDto), HttpStatus.OK);
    }
}
