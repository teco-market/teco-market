package com.teco.market.domain.category.service;

import org.springframework.stereotype.Service;

import com.teco.market.domain.category.Category;
import com.teco.market.domain.category.CategoryRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }
}
