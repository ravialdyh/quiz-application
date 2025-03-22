package com.quizapp.service;

import com.quizapp.model.entity.Role;

import java.util.Set;

public interface RoleService {
    Role findByName(String name);
    Set<Role> getDefaultRoles();
    Set<Role> getRolesByNames(Set<String> roleNames);
}