package com.coder.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private int categroyId;
    private String categoryTitle;
    private String categoryDescription;

}
