package com.spring.jwt.auth.api.entity;

import com.spring.jwt.auth.api.entity.type.IdTypeEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("사용자 아이디(이메일)")
    private String userId;

    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("사용자 패스워드")
    private String password;

    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("사용자 이름")
    private String name;

    @Enumerated(EnumType.STRING)
    @Comment("사용자 유형(REG_NO,BUSINESS_NO)")
    private IdTypeEnum idType;

    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("유형 값(주민등록번호,사업자등록번호)")
    private String idValue;

    @CreationTimestamp
    @Column(updatable = false)
    @Comment("등록 시간")
    private LocalDateTime regDttm;

    @UpdateTimestamp
    @Comment("업데이트 시간")
    private LocalDateTime updDttm;

    // DTO -> Entity 변환을 위한 public 생성자
    @Builder
    public Member(String userId, String password, String name, IdTypeEnum idType, String idValue) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.idType = idType;
        this.idValue = idValue;
    }
}
