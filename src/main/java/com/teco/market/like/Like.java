package com.teco.market.like;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_like")
public class Like {
    @Id
    @GeneratedValue
    private Long id;
}
