package com.hotel.Paradise_Hotel.repository;

import com.hotel.Paradise_Hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);


    void deleteByEmail(String email);
    Optional<User>findById(Long userId);


    Optional<User> findByEmail(String email);
}
