package com.teco.market.domain.category;

import javax.persistence.Entity;

import com.teco.market.domain.BaseEntity;
import lombok.Getter;

@Getter
@Entity
public class Category extends BaseEntity {

    private String name;
}
