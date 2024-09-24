package com.hotel.Paradise_Hotel.controller;


import com.hotel.Paradise_Hotel.exception.RoleAlreadyExistException;
import com.hotel.Paradise_Hotel.model.Role;
import com.hotel.Paradise_Hotel.model.User;
import com.hotel.Paradise_Hotel.service.RoleService;
import com.hotel.Paradise_Hotel.service.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private  final RoleService roleService;


    @GetMapping("/all-roles")
    public ResponseEntity<List<Role>> getAllRoles(){
        return  new ResponseEntity<List<Role>>(roleService.getRoles(), HttpStatus.FOUND);
    }

    @PostMapping("/create-new-role")
    public ResponseEntity<String> createRole(@RequestBody Role theRole){
        try {
            roleService.createRole(theRole);
            return ResponseEntity.ok("New Role Created Successfully !");
        }catch (RoleAlreadyExistException e){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{roleId}")
    public void deleteRole(@PathVariable("roleId") Long roleId){
        roleService.deleteRole(roleId);
    }

    @PostMapping("/remove-all-users-from-role/{roleId}")
    public Role removeAllUsersFromRole(@PathVariable("roleId") Long roleId){
        return roleService.removeAllUserFromRole(roleId);
    }

    @PostMapping("/remove-user-from-role")
    public User removeUserFromRole(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId){
        return  roleService.removeUserFromRole(userId, roleId);
    }

    @PostMapping("/assign-user-to-role")
    public  User assignRoleToUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId){
        return  roleService.assignRoleToUser(userId,roleId);

    }

}

