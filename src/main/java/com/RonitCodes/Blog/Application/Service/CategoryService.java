package com.RonitCodes.Blog.Application.Service;

import com.RonitCodes.Blog.Application.Dto.Category.CategoryDto;
import com.RonitCodes.Blog.Application.Model.Category;
import com.RonitCodes.Blog.Application.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    // ✅ Create a new category
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category newCategory = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(newCategory);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    // ✅ Update existing category by ID
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        // Map updated fields manually or by ModelMapper
        existing.setCategoryTitle(categoryDto.getCategoryTitle());
        existing.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updated = categoryRepository.save(existing);
        return modelMapper.map(updated, CategoryDto.class);
    }

    // ✅ Get all categories
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    // ✅ Get category by ID
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));
        return modelMapper.map(category, CategoryDto.class);
    }

    // ✅ Delete category
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));
        categoryRepository.delete(category);
    }
}
