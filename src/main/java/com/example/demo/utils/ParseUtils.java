package com.example.demo.utils;

import com.example.demo.constants.OrderConstants;
import com.example.demo.domain.Cart;
import com.example.demo.domain.Orders;
import com.example.demo.dto.filter.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ParseUtils {
    private final ModelMapper modelMapper;

    public List<SearchCriteria> parseSearch(String search) {
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?)(,|;)", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            searchCriteriaList.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
        }
        return searchCriteriaList;
    }

    public Orders parseCartToOrders(Cart cart) {
        Orders orders = Orders.builder()
                .totalPrice(cart.getTotalPrice())
                .status(OrderConstants.CREATED)
                .orderDetail(cart.getCartItem().stream().map(cartItems -> modelMapper.map(cartItems, com.example.demo.domain.OrderDetail.class)).collect(Collectors.toSet())).build();
        return orders;
    }
}
