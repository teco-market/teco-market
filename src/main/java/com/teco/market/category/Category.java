package com.teco.market.category;

import javax.persistence.Entity;

import com.teco.market.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category extends BaseEntity {

    private String name;

    public Category(String name) {
        this.name = name;
    }
}
