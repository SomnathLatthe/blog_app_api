package com.somnath.blogappapis.services.Impl;

import com.somnath.blogappapis.entities.Category;
import com.somnath.blogappapis.exceptions.ResourceNotFoundException;
import com.somnath.blogappapis.payloads.CategoryDto;
import com.somnath.blogappapis.repositories.CategoryRepo;
import com.somnath.blogappapis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory=this.categoryRepo.save(category);
        return this.modelMapper.map(addedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));/* String resourceName,String fieldName,long fieldValue*/
        category.setCategoryTitle(category.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categoryList=this.categoryRepo.findAll();
        List<CategoryDto> categoryDtoList=categoryList.stream().map((category) -> this.modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());

        return categoryDtoList;
    }
}
