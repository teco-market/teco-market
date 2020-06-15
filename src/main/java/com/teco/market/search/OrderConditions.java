package com.teco.market.search;

import static com.teco.market.like.QLike.*;
import static com.teco.market.post.QPost.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;

public class OrderConditions {
    private final SearchCondition searchCondition;
    private List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

    public OrderConditions(SearchCondition searchCondition) {
        this.searchCondition = searchCondition;
        addLike();
        addPriceAsc();
        addPriceDesc();
        addRecent();
    }

    public OrderSpecifier[] getConditions() {
        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }

    private void addLike() {
        if (!StringUtils.isEmpty(searchCondition.getByLike())) {
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, like.post.count()));
        }
    }

    private void addPriceAsc() {
        if (!StringUtils.isEmpty(searchCondition.getByPriceAsc())) {
            orderSpecifiers.add(new OrderSpecifier(Order.ASC, post.price));
        }
    }

    private void addPriceDesc() {
        if (!StringUtils.isEmpty(searchCondition.getByPriceDesc())) {
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, post.price));
        }
    }

    private void addRecent() {
        if (!StringUtils.isEmpty(searchCondition.getByRecent())) {
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, post.createdAt));
        }
    }
}
