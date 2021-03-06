package com.teco.market.generation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.teco.market.BaseEntity;
import com.teco.market.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Generation extends BaseEntity {
    private String alias;

    public Generation(String alias) {
        this.alias = alias;
    }

    @Builder
    public Generation(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String alias) {
        super(id, createdAt, updatedAt);
        this.alias = alias;
    }
}
