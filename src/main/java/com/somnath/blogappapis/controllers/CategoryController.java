package com.somnath.blogappapis.controllers;

import com.somnath.blogappapis.payloads.ApiResponse;
import com.somnath.blogappapis.payloads.CategoryDto;
import com.somnath.blogappapis.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
    {
        CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer catId)
    {
        CategoryDto createCategory=this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer catId)
    {
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully !",true),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer catId)
    {
        CategoryDto categoryDto=this.categoryService.getCategory(catId);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory()
    {
        List<CategoryDto> categoryDtoList=this.categoryService.getCategories();
        return ResponseEntity.ok(categoryDtoList);
    }
}

