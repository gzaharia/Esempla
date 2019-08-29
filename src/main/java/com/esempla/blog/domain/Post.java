package com.esempla.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created")
    private Date created_date;

    @Lob
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "blog_id",nullable = false)
    private Blog blogId;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category categoryId;

    @OneToMany(mappedBy = "postId")
    Set<Comments> comments;



}
