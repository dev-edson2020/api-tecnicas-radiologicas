package com.api.tecnicasradiologicas.service;

import com.api.tecnicasradiologicas.dto.CategoryDTO;
import com.api.tecnicasradiologicas.model.Category;
import com.api.tecnicasradiologicas.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getById(Long id) {
        return categoryRepository.findById(id).map(this::toDTO);
    }

    public List<CategoryDTO> createMultiple(List<CategoryDTO> dtos) {
        List<Category> categories = dtos.stream()
                .map(dto -> new Category(dto.getName()))
                .collect(Collectors.toList());
        return categoryRepository.saveAll(categories).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> update(Long id, CategoryDTO dto) {
        return categoryRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            return toDTO(categoryRepository.save(existing));
        });
    }

    public boolean delete(Long id) {
        return categoryRepository.findById(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
    }

    private CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
