package com.api.tecnicasradiologicas.controller;

import com.api.tecnicasradiologicas.dto.CategoryDTO;
import com.api.tecnicasradiologicas.model.Category;
import com.api.tecnicasradiologicas.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categories")
@CrossOrigin(origins = "*") // para permitir acesso do frontend
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable String id) {
        return categoryRepository.findById(id)
                .map(cat -> ResponseEntity.ok(cat))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Category>> createMultiple(@RequestBody List<CategoryDTO> categoriesDto) {
        List<Category> categories = categoriesDto.stream()
                .map(dto -> new Category(dto.getId(), dto.getName()))
                .collect(Collectors.toList());
        List<Category> saved = categoryRepository.saveAll(categories);
        return ResponseEntity.ok(saved);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable String id, @RequestBody Category category) {
        return categoryRepository.findById(id).map(existing -> {
            existing.setName(category.getName());
            categoryRepository.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return categoryRepository.findById(id).map(existing -> {
            categoryRepository.delete(existing);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
