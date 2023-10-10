package com.coder.service;

import com.coder.Entity.Category;
import com.coder.Entity.CategoryDto;
import com.coder.execpation.ResourceNotFoundExpection;
import com.coder.repo.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategory{
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto category) {
       Category category1= this.modelMapper.map(category,Category.class);
       Category cat= this.categoryRepo.save(category1);
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto category, int id) {
        Category category1=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundExpection("Category","id",id));
        category1.setCategoryTitle(category.getCategoryTitle());
        category1.setCategoryDescription(category.getCategoryDescription());
        Category cat=this.categoryRepo.save(category1);

        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategoryAll() {
        List<Category> list=this.categoryRepo.findAll();
       List<CategoryDto>categoryDtos=list.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategroyById(Integer id) {
       Category category= this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundExpection("Categroy","id",id));

        return this.modelMapper.map(category,CategoryDto.class);}

    @Override
    public void deleteCategoryById(Integer id) {
    Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundExpection("Categroy","id",id));
    this.categoryRepo.delete(category);
    }
}
