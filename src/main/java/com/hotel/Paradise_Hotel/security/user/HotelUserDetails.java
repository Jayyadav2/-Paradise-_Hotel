package com.hotel.Paradise_Hotel.security.user;

import com.hotel.Paradise_Hotel.model.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HotelUserDetails implements UserDetails {
    private  Long id;
    private String email;
    private String password;

    private Collection<GrantedAuthority> authorities;

    public static HotelUserDetails buildUserDetails(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return  new HotelUserDetails(user.getId(), user.getEmail(), user.getPassword(), authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    public  boolean isAccountNonExpired(){
        return  true;
    }

    public  boolean isAccountNonLocked(){
        return true;
    }

    public boolean isCredentialsNonExpired(){
        return  true;
    }

    public  boolean isEnabled(){
        return  true;
    }
}
