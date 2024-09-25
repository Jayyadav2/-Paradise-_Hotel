package com.hotel.Paradise_Hotel.repository;

import com.hotel.Paradise_Hotel.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleUser);

    default boolean existsByName(Role role) {
        return false;
    }
}
