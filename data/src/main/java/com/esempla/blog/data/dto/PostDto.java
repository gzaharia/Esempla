package com.esempla.blog.data.dto;


import com.esempla.blog.data.domain.Category;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String name;
    private String content;
    private Long categoryId;
    private Long blogId;
    private Date createdDate;

}
