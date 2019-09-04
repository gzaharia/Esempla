package com.esempla.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comments {
    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    private Long id;

    @Lob
    @Column(name = "text")
    private String text;

    @Column(name = "created")
    private Date created_date;

//    @ManyToOne
//    @JoinColumn(name = "post_id",nullable = false)
//    private Post postId;



}
