package com.example.study_gather.category;

import com.example.study_gather.category.dto.CategoryListResponse;
import com.example.study_gather.category.dto.CreateCategoryRequest;
import com.example.study_gather.category.dto.CreateCategoryResponse;
import com.example.study_gather.common.security.JwtFilter;
import com.example.study_gather.common.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CreateCategoryResponse createCategory(@RequestBody CreateCategoryRequest request,
                                                 @AuthenticationPrincipal JwtProvider.JwtUserPrincipal principal) {
        Long memberId = principal.getMemberId();
        return categoryService.createCategory(request, memberId);
    }

    @GetMapping
    public CategoryListResponse getCategoryList() {
        return categoryService.getCategoryList();
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId, @AuthenticationPrincipal JwtProvider.JwtUserPrincipal principal) {
        Long memberId = principal.getMemberId();
        categoryService.deleteCategory(categoryId, memberId);
    }
}
