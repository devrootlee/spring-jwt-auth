package com.spring.jwt.auth.api.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.IllegalBlockSizeException;
import java.util.LinkedHashMap;

@RestControllerAdvice(basePackages = {"com.moin.backend.assignment.api.controller"})
public class ExceptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * NULL 오류
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity NullPointerException(NullPointerException e) {
        LinkedHashMap body = new LinkedHashMap();

        body.put("resultCode",ValidationCode.SERVER_ERROR.getCode());

        LOGGER.error(e.getMessage(), e);

        return ResponseEntity.status(ValidationCode.SERVER_ERROR.getCode()).body(body);
    }

    /**
     * 필수 파라미터 누락 예외처리
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity MissingServletRequestParameterException(MissingServletRequestParameterException e){
        LinkedHashMap body = new LinkedHashMap();

        body.put("resultCode",ValidationCode.REQUEST_ERROR.getCode());

        LOGGER.error(e.getMessage(), e);

        return ResponseEntity.status(ValidationCode.REQUEST_ERROR.getCode()).body(body);
    }

    /**
     * 파라미터 Validation 오류
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseEntity BindException(BindException e){
        LinkedHashMap body = new LinkedHashMap();

        body.put("resultCode",ValidationCode.REQUEST_ERROR.getCode());

        LOGGER.error(e.getMessage(), e);

        return ResponseEntity.status(ValidationCode.REQUEST_ERROR.getCode()).body(body);
    }

    /**
     * 잘못된 P값 오류
     * @param e
     * @return
     */
    @ExceptionHandler(value = IllegalBlockSizeException.class)
    public ResponseEntity IllegalBlockSizeException(IllegalBlockSizeException e){
        LinkedHashMap body = new LinkedHashMap();

        body.put("resultCode",ValidationCode.REQUEST_ERROR.getCode());

        LOGGER.error(e.getMessage(), e);

        return ResponseEntity.status(ValidationCode.REQUEST_ERROR.getCode()).body(body);
    }

    /**
     * userId가 중복일 경우
     * @param e
     * @return
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException e) {
        LinkedHashMap body = new LinkedHashMap();

        body.put("resultCode",ValidationCode.REQUEST_ERROR.getCode());
        body.put("resultMsg","잘못된 파라미터 입니다.");

        LOGGER.error(e.getMessage(), e);

        // 예외 메시지와 상태 코드를 반환
        return ResponseEntity.status(ValidationCode.REQUEST_ERROR.getCode()).body(body);
    }

    /**
     * userId가 이메일 형식이 아닐경우
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LinkedHashMap body = new LinkedHashMap();

        body.put("resultCode",ValidationCode.REQUEST_ERROR.getCode());
        body.put("resultMsg","잘못된 파라미터 입니다.");

        LOGGER.error(e.getMessage(), e);

        // 예외 메시지와 상태 코드를 반환
        return ResponseEntity.status(ValidationCode.REQUEST_ERROR.getCode()).body(body);
    }

    /**
     * idType이 알맞은 형식이 아닐경우
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LinkedHashMap body = new LinkedHashMap();

        body.put("resultCode",ValidationCode.REQUEST_ERROR.getCode());
        body.put("resultMsg","잘못된 파라미터 입니다.");

        LOGGER.error(e.getMessage(), e);

        // 예외 메시지와 상태 코드를 반환
        return ResponseEntity.status(ValidationCode.REQUEST_ERROR.getCode()).body(body);
    }

    // 그 외 오류
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity Exception(Exception e){
        LinkedHashMap body = new LinkedHashMap();

        body.put("resultCode",ValidationCode.SERVER_ERROR.getCode());

        LOGGER.error(e.getMessage(), e);

        return ResponseEntity.status(ValidationCode.SERVER_ERROR.getCode()).body(body);
    }


}
