package com.hotel.Paradise_Hotel.service;

import com.hotel.Paradise_Hotel.model.Role;
import com.hotel.Paradise_Hotel.model.User;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();
    Role createRole(Role theRole);

    void deleteRole(Long id);

    Role findByName(String name);

    User removeUserFromRole(Long userId, Long roleId);

    User assignRoleToUser(Long userId, Long roleId);

    Role removeAllUserFromRole(Long roleId);
}
