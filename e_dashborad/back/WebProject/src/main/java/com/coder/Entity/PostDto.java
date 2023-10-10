package com.coder.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostDto {
        private int id;
        private String title;
        private String content;
        private String imageName;
        private Date addDate;
        private CategoryDto category;
        private UserDto user;
        private Set<Comments>comments=new HashSet<>();

}
