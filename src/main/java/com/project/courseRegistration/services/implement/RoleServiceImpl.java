package com.project.courseRegistration.services.implement;

import com.project.courseRegistration.models.Role;
import com.project.courseRegistration.repositories.RoleRepository;
import com.project.courseRegistration.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
