package com.hotel.Paradise_Hotel.service;

import com.hotel.Paradise_Hotel.exception.RoleAlreadyExistException;
import com.hotel.Paradise_Hotel.exception.UserAlreadyExistsException;
import com.hotel.Paradise_Hotel.model.Role;
import com.hotel.Paradise_Hotel.model.User;
import com.hotel.Paradise_Hotel.repository.RoleRepository;
import com.hotel.Paradise_Hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements  RoleService{
    private  final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private  final  UserService userService;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role theRole) {
        String roleName= "ROLE_"+theRole.getName().toUpperCase();
        Role role= new Role(roleName);
        if(roleRepository.existsByName(role)){
            throw  new RoleAlreadyExistException(theRole.getName()+" Role Already Exists");
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removeAllUserFromRole(roleId);
        roleRepository.deleteById(roleId);

    }

    @Override
    public Role findByName(String name) {

        return roleRepository.findByName(name).get();
    }

    @Override
    public User removeUserFromRole(Long userId, Long roleId) {
       Optional<User> user =userRepository.findById(userId);
       Optional<Role>  role= roleRepository.findById(roleId);
        if(role.isPresent() && role.get().getUsers().contains(user.get())){
            role.get().removeUserFromRole(user.get());
            roleRepository.save(role.get());
            return user.get();
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        Optional<User> user =userRepository.findById(userId);
        Optional<Role>  role= roleRepository.findById(roleId);
        if(user.isPresent() && user.get().getRoles().contains(role.get())){
            throw new UserAlreadyExistsException(user.get().getFirstName()+" is already assigned to the" + role.get().getName()+ "role");
        }
        if(role.isPresent()){
            role.get().assignRoleToUser(user.get());
            roleRepository.save(role.get());

        }
        return user.get();
    }

    @Override
    public Role removeAllUserFromRole(Long roleId) {
        Optional<Role> role= roleRepository.findById(roleId);
        role.get().removeAllUsersFromRole();

        return roleRepository.save(role.get());
    }
}
