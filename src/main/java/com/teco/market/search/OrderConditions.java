package com.teco.market.search;

import static com.teco.market.like.QLike.*;
import static com.teco.market.post.QPost.*;
import static org.springframework.util.StringUtils.*;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;

public class OrderConditions {
    private final SearchCondition searchCondition;
    private final List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

    public OrderConditions(SearchCondition searchCondition) {
        this.searchCondition = searchCondition;
        addLike();
        addPriceAsc();
        addPriceDesc();
        addRecent();
    }

    public OrderSpecifier[] get() {
        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }

    private void addLike() {
        if (!isEmpty(searchCondition.getByLike())) {
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, like.post.count()));
        }
    }

    private void addPriceAsc() {
        if (!isEmpty(searchCondition.getByPriceAsc())) {
            orderSpecifiers.add(new OrderSpecifier(Order.ASC, post.price));
        }
    }

    private void addPriceDesc() {
        if (!isEmpty(searchCondition.getByPriceDesc())) {
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, post.price));
        }
    }

    private void addRecent() {
        if (!isEmpty(searchCondition.getByRecent())) {
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, post.createdAt));
        }
    }
}
