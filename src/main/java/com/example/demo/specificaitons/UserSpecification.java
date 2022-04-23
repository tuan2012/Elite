package com.example.demo.specificaitons;

import com.example.demo.domain.User;
import com.example.demo.dto.request.UserFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {

    private UserFilterDto criteria;

    public UserSpecification(UserFilterDto criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getName() != null) {
            predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), new StringBuilder().append("%").append(criteria.getName()).append("%").toString())));
        }
        if (criteria.getUsername() != null) {
            predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("username"), new StringBuilder().append("%").append(criteria.getUsername()).append("%").toString())));
        }
        if (criteria.getEmail() != null) {
            predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("email"), new StringBuilder().append("%").append(criteria.getEmail()).append("%").toString())));
        }
        if (criteria.getActive() != null) {
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("isDeleted"), criteria.getActive())));
        }
        if (criteria.getDeleted() != null) {
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("isActive"), criteria.getDeleted())));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}

