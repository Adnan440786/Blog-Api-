package com.coder.service;

import com.coder.Entity.Category;
import com.coder.Entity.CategoryDto;

import java.util.List;

public interface ICategory {


            CategoryDto createCategory(CategoryDto category);

            CategoryDto updateCategory(CategoryDto category, int id);

            List<CategoryDto> getCategoryAll();

            CategoryDto getCategroyById(Integer id);

            void deleteCategoryById(Integer id);



}
