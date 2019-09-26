package com.esempla.blog.data.dto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String name;
    private String content;
    private Long categoryId;
    private Long blogId;
    private Long commentId;

}
