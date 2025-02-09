# spring-jwt-auth
## 스프링 jwt 회원가입,로그인 API

#### 패키지 구조
```
spring-jwt-auth.
│  .gitignore : 
│  build.gradle : 
│  README.md
└─src
    └─main
        ├─java
        │  └─com
        │      └─spring
        │          └─jwt
        │              └─auth
        │                  └─api
        │                      │  SpringJwtAuthApplication.java : main
        │                      │
        │                      ├─common
        │                      │  ├─exception
        │                      │  │      ExceptionController.java : @RestControllerAdvice를 사용한 일괄적 예외처리
        │                      │  │      ValidationCode.java : http 상태코드 enum
        │                      │  │
        │                      │  ├─security
        │                      │  │      EncryptionConfig.java : 개인정보 암호화 설정(password,idValue)
        │                      │  │      JwtAuthenticationFilter.java : jwt 필터
        │                      │  │      JwtProvider.java : jwt 생성, 
        │                      │  │      SecurityConfig.java : spring security 설정
        │                      │  │
        │                      │  └─util
        │                      │          CommonUtil.java : 공통유틸(return response)
        │                      │
        │                      ├─controller
        │                      │      MemberController.java : member controller(signUp, login)
        │                      │
        │                      ├─dto
        │                      │      MemberDto.java : member controller request dto(signUp, login), 입력받은 값 검증
        │                      │
        │                      ├─entity
        │                      │  │  Member.java : member entity(userId(이메일, PK), password(암호), name(이름), idType(사용자 유형), idValue(유형별 개인정보값 : 주민등록번호, 사업자등록번호) 
        │                      │  │
        │                      │  └─type
        │                      │          IdTypeEnum.java : idType 유형 enum(REG_NO,BUSINESS_NO)
        │                      │
        │                      ├─repository
        │                      │      MemberRepository.java : JpaRepository를 상속받은 리포지토리 인터페이스
        │                      │
        │                      └─service
        │                              MemberService.java : member servie(signUp : 입력받은 회원 정보 저장(password,idValue 암호화), login : 입력받은 암호를 암호화 한 값이 db에 있는 암호와 일치하는지 확인, jwt토큰 생성)  
        │
        └─resources
                application.yml : Spring Boot 애플리케이션 설정 파일
                logback-spring.xml : log 설정 파일
```