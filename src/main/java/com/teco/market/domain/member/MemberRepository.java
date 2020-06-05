package com.teco.market.domain.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teco.market.oauth2.ui.user.PlatformType;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByPlatformIdAndPlatformType(String sub, PlatformType google);
}
