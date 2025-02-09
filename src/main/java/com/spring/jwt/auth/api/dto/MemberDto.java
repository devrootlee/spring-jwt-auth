package com.spring.jwt.auth.api.dto;

import com.spring.jwt.auth.api.entity.type.IdTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class MemberDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class signUpDto {
        @NotBlank
        @Email
        private String userId;

        @NotBlank
        private String password;

        @NotBlank
        private String name;

        @NotNull
        private IdTypeEnum idType;

        @NotBlank
        private String idValue;
    }

    @Getter
    @Setter
    public static class loginDto {
        @NotBlank
        @Email
        private String userId;

        @NotBlank
        private String password;
    }
}


