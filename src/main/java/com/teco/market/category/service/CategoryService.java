package com.teco.market.category.service;

import org.springframework.stereotype.Service;

import com.teco.market.category.Category;
import com.teco.market.category.CategoryRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }
}
