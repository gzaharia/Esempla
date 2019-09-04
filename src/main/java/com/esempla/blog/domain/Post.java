package com.esempla.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {

    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created")
    private Date created_date;

    //@Lob
    @Column(name = "content")
    private String content;

    @ManyToOne
    private Category category;

//    @ManyToOne
//    @JoinColumn(name = "blog_id",nullable = false)
//    private Blog blogId;

//    @ManyToOne
//    @JoinColumn(name = "category_id",nullable = false)
//    private Category categoryId;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<Comments> comments;



}
