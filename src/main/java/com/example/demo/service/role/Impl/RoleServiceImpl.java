package com.example.demo.service.role.Impl;

import com.example.demo.domain.Role;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundException("Role not found"));
    }
}
