package com.example.demo.service.Impl.cart;

import com.example.demo.domain.Cart;
import com.example.demo.service.CartCRUDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class CartCRUDServiceImpl implements CartCRUDService {
    @Override
    public Cart save(Cart cart) {
        return null;
    }

    @Override
    public Cart findById(UUID id) {
        return null;
    }

    @Override
    public Page<Cart> findAll(Specification specification, Pageable pageable) {
        return null;
    }
}
