package com.teco.market.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teco.market.oauth2.user.PlatformType;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.platformId=:platformId and m.platformType=:platformType")
    Optional<Member> findByPlatform(@Param("platformId") String platformId, @Param("platformType") PlatformType platformType);
}
