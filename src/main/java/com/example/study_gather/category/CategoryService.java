package com.example.study_gather.category;

import com.example.study_gather.category.dto.CategoryListResponse;
import com.example.study_gather.category.dto.CreateCategoryRequest;
import com.example.study_gather.category.dto.CreateCategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    @Transactional
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        if (request.parentId() == null) {
            Category category = categoryRepository.save(new Category(null, request.name()));
            return new CreateCategoryResponse(category.getId(), category.getParentId(), category.getName());
        } else {
            Category parentCategory = categoryRepository.findById(request.parentId()).orElseThrow(
                    () -> new NoSuchElementException("해당하는 부모 카테고리가 존재하지 않습니다."));
            Category category = categoryRepository.save(new Category(request.parentId(), request.name()));
            return new CreateCategoryResponse(category.getId(), category.getParentId(), category.getName());
        }
    }

    public CategoryListResponse getCategoryList() {
        List<CategoryResponse> categoryResponses = categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getParentId(),
                        category.getName()))
                .toList();
        return new CategoryListResponse(categoryResponses);
    }
}
