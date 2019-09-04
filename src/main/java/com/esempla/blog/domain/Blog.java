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
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private AppUser appUser;

//    @Column(name = "user_id")
//    private Integer user_id;

    @Column(name = "created")
    private Date created_date;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<Post> posts = new HashSet<>();


}
