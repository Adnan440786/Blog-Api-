package com.coder.controllers;

import com.coder.Entity.Category;
import com.coder.Entity.CategoryDto;
import com.coder.execpation.ApiResponse;
import com.coder.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/api")
public class CategoryController {

        @Autowired
        private CategoryService categoryService;

        // post-create Category
        @PostMapping("/save")
        public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto category){
            CategoryDto category1=this.categoryService.createCategory(category);
            return new ResponseEntity<CategoryDto>(category1, HttpStatus.CREATED);
        }


        // put-update Category
            @PutMapping("/update/{id}")
            public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto category, @PathVariable("id")Integer id){
                CategoryDto category1=this.categoryService.updateCategory(category,id);
            return new ResponseEntity<CategoryDto>(category1,HttpStatus.OK);
            }
        // Delete-Categroy
            @DeleteMapping("/deleteId/{id}")
            public ResponseEntity<ApiResponse> deleteById(@PathVariable("id") Integer id){
            this.categoryService.deleteCategoryById(id);
            return new ResponseEntity<ApiResponse>(new ApiResponse("Delete SuccessFully",true),HttpStatus.OK);
            }
        // Get- getCategory List
            @GetMapping("/List")
            public ResponseEntity<List<CategoryDto>> getCategoryList(){

            return ResponseEntity.ok(this.categoryService.getCategoryAll());
            }

        // Get- getCategoryBy Id
            @GetMapping("/categoryId/{id}")
            public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Integer id){
                return ResponseEntity.ok(this.categoryService.getCategroyById(id));
            }

}
