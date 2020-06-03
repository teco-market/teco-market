package com.teco.market.domain.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
