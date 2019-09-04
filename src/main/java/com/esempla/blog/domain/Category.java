package com.esempla.blog.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

}
