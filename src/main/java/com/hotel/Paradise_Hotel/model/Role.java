package com.hotel.Paradise_Hotel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new HashSet<>();

    public Role(String roleName) {
        this.name= roleName;
    }

    public  void  assignRoleToUser(User user){
        user.getRoles().add(this);
        this.getUsers().add(user);

    }
    public  void  removeUserFromRole( User user){
        user.getRoles().remove(this);
        this.getUsers().remove(user);

    }

    public void removeAllUsersFromRole(){
        if(this.getUsers()!=null){
            List<User> roleUsers= this.getUsers().stream().toList();
            for (User roleUser : roleUsers) {

                removeUserFromRole(roleUser);
            }
        }
    }
    public String getName(){
        return name!= null? name:"";
    }
}
