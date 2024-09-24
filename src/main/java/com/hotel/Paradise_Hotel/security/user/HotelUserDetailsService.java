package com.hotel.Paradise_Hotel.security.user;

import com.hotel.Paradise_Hotel.model.User;
import com.hotel.Paradise_Hotel.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
@Component
public class HotelUserDetailsService implements UserDetailsService {

    @Autowired
    private  final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        return HotelUserDetails.buildUserDetails(user);
    }
}
