package com.spring.jwt.auth.api.common.util;

import com.spring.jwt.auth.api.common.exception.ValidationCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 공통 util
 */
@Component
public class CommonUtil {

    /**
     * controller response
     * @param data
     * @param status
     * @return
     */
    public ResponseEntity<Map<String, Object>> successResponseUtil(Map data, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("resultCode", ValidationCode.SUCCESS.getCode());
        body.put("resultMsg","OK");

        if (data != null) {
            body.putAll(data);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
