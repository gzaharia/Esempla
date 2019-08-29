package com.esempla.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @Enumerated(EnumType.STRING)
    private RolesType name;


    @ManyToMany(mappedBy = "usersRoles")
    Set<Users> users;

}
