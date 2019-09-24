package com.esempla.blog.data.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @ManyToMany(mappedBy = "usersRoles")
    Set<AppUser> users;

}
