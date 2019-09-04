package com.esempla.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<Blog> blogs = new HashSet<>();

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    private Boolean enabled;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "created")
    private Date created_date;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="role_name")
    )
    Set<Roles> usersRoles = new HashSet<>();
}
