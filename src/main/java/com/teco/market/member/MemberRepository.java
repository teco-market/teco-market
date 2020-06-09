package com.teco.market.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teco.market.oauth2.user.PlatformType;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByPlatformIdAndPlatformType(String platformId, PlatformType platformType);
}
