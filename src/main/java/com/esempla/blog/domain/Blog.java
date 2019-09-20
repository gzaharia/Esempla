package com.esempla.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private AppUser appUser;

    @Column(name = "created")
    private LocalDate created_date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blog")
    Set<Post> posts = new HashSet<>();


}
