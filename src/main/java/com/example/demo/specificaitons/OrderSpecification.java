package com.example.demo.specificaitons;

import com.example.demo.domain.Orders;
import com.example.demo.dto.request.OrdersFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification implements Specification<Orders> {

    private OrdersFilterDto criteria;

    public OrderSpecification(OrdersFilterDto criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getOrderUuid() != null) {
            predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("order_uuid"), new StringBuilder().append("%").append(criteria.getOrderUuid()).append("%").toString())));
        }
        if (criteria.getUserUuid() != null) {
            predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("user_uuid"), new StringBuilder().append("%").append(criteria.getUserUuid()).append("%").toString())));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}