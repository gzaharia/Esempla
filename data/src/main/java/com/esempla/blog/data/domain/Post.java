package com.esempla.blog.data.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created")
    private Date created_date;

    @Column(name = "content")
    private String content;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Blog blog;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Comments> comments;
}
