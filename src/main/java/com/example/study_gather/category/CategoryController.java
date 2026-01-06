package com.example.study_gather.category;

import com.example.study_gather.category.dto.CategoryListResponse;
import com.example.study_gather.category.dto.CreateCategoryRequest;
import com.example.study_gather.category.dto.CreateCategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    public CreateCategoryResponse createCategory(@RequestBody CreateCategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @GetMapping
    public CategoryListResponse getCategoryList() {
        return categoryService.getCategoryList();
    }
}
