package com.teco.market.domain.member;

import javax.persistence.Entity;

import com.teco.market.domain.BaseEntity;
import com.teco.market.oauth2.ui.user.PlatformType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {
    private String platformId;
    private PlatformType platformType;
    private String name;

    public Member(String platformId, PlatformType platformType, String name) {
        this.platformId = platformId;
        this.platformType = platformType;
        this.name = name;
    }
}
