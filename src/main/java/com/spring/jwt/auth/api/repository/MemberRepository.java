package com.spring.jwt.auth.api.repository;

import com.spring.jwt.auth.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
