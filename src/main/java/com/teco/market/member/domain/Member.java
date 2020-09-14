package com.teco.market.member.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.teco.market.BaseEntity;
import com.teco.market.generation.Generation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {
    @Column(unique = true)
    private Long kakaoId;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String nickname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "generation_id")
    private Generation generation;

    public Member(Long kakaoId, String name, String email, Role role) {
        this(null, null, null, kakaoId, name, email, role);
    }

    @Builder
    public Member(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long kakaoId, String name, String email,
        Role role) {
        super(id, createdAt, updatedAt);
        this.kakaoId = kakaoId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public void updateRequiredInfo(String nickname, Generation generation) {
        this.nickname = nickname;
        setGeneration(generation);
        changeRole();
    }

    private void setGeneration(Generation generation) {
        this.generation = generation;
    }

    private void changeRole() {
        this.role = Role.USER;
    }

    public void updateRequiredInfo(String name, String nickname, String email) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }
}
