package com.teco.market.member;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.teco.market.BaseEntity;
import com.teco.market.generation.Generation;
import com.teco.market.oauth2.user.PlatformType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(indexes = {@Index(name = "platform", columnList = "platformId, platformType")})
public class Member extends BaseEntity {
    private String platformId;
    @Enumerated(EnumType.STRING)
    private PlatformType platformType;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id")
    private Generation generation;

    public Member(String platformId, PlatformType platformType, String name, String email, Role role) {
        this(null, LocalDateTime.now(), null, platformId, platformType, name, email, role);
    }

    @Builder
    public Member(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String platformId,
        PlatformType platformType, String name, String email, Role role) {
        super(id, createdAt, updatedAt);
        this.platformId = platformId;
        this.platformType = platformType;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public void setRequiredInfo(String nickname, Generation generation) {
        this.nickname = nickname;
        setGeneration(generation);
    }

    private void setGeneration(Generation generation) {
        this.generation = generation;
        generation.addMember(this);
    }

    public void changeRole() {
        this.role = Role.USER;
    }
}
