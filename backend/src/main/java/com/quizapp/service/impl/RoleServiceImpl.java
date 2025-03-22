package com.quizapp.service.impl;

import com.quizapp.exception.ResourceNotFoundException;
import com.quizapp.model.entity.Role;
import com.quizapp.repository.RoleRepository;
import com.quizapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    
    @Value("${quizapp.security.default-role:ROLE_STUDENT}")
    private String defaultRole;
    
    private static final String ROLE_PREFIX = "ROLE_";

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        // Ensure role name has ROLE_ prefix
        String roleName = ensureRolePrefix(name);
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + roleName));
    }

    @Override
    public Set<Role> getDefaultRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(findByName(defaultRole));
        return roles;
    }

    @Override
    public Set<Role> getRolesByNames(Set<String> roleNames) {
        Set<Role> roles = new HashSet<>();
        if (roleNames != null && !roleNames.isEmpty()) {
            roleNames.forEach(roleName -> roles.add(findByName(roleName)));
        } else {
            // If no roles specified, assign default roles
            roles.addAll(getDefaultRoles());
        }
        return roles;
    }
    
    /**
     * Ensures that the role name has the ROLE_ prefix.
     * 
     * @param roleName the role name to check
     * @return the role name with ROLE_ prefix
     */
    private String ensureRolePrefix(String roleName) {
        return roleName.startsWith(ROLE_PREFIX) ? roleName : ROLE_PREFIX + roleName;
    }
}