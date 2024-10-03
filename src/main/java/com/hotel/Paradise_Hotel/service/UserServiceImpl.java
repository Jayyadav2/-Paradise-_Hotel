package com.hotel.Paradise_Hotel.service;

import com.hotel.Paradise_Hotel.exception.UserAlreadyExistsException;
import com.hotel.Paradise_Hotel.model.Role;
import com.hotel.Paradise_Hotel.model.User;
import com.hotel.Paradise_Hotel.repository.RoleRepository;
import com.hotel.Paradise_Hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final  RoleRepository roleRepository;


    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userRepository.existsByEmail(user.getEmail())){
            throw  new UserAlreadyExistsException(user.getEmail() +" already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole= roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));

        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        User theUser= getUser(email);
        if(theUser !=null) {
            userRepository.deleteByEmail(email);
        }

    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User No Found"));
    }
}
