package com.example.demo.service.Impl.orders;

import com.example.demo.domain.Orders;
import com.example.demo.domain.User;
import com.example.demo.dto.filter.SearchCriteria;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.service.OrdersCRUDService;
import com.example.demo.specifications.SpecificationBuilder;
import com.example.demo.utils.ParseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersCRUDServiceImpl implements OrdersCRUDService {
    private final OrdersRepository ordersRepository;
    private final ParseUtils parseUtils;
    private final SpecificationBuilder specificationBuilder;

    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public Orders findById(UUID id) {
        return ordersRepository.findById(id).orElseThrow(() -> new NotFoundException("Orders not found"));
    }

    @Override
    public Page<Orders> findAll(Specification specification, Pageable pageable) {
        return ordersRepository.findAll(specification, pageable);
    }

    @Override
    public PageResponseDto<Orders> findAll(UUID userUuid, int page, int size, String sortType, String sortBy, String search) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        List<SearchCriteria> searchCriteria = parseUtils.parseSearch(search);
        if (userUuid != null) {
            searchCriteria.add(new SearchCriteria("user", ":", new User(userUuid)));
        }
        Page<Orders> ordersPage = ordersRepository.findAll(specificationBuilder.createSpecification(searchCriteria), pageable);
        PageResponseDto pageResponseDto = PageResponseDto.builder()
                .page(page)
                .size(size)
                .totalPages(ordersPage.getTotalPages())
                .totalElements(ordersPage.getTotalElements())
                .elements(ordersPage.getContent().stream().map(orders -> orders).collect(Collectors.toList())).build();
        return pageResponseDto;
    }
}
