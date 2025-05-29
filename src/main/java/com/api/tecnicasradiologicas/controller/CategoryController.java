package com.api.tecnicasradiologicas.controller;

import com.api.tecnicasradiologicas.dto.CategoryDTO;
import com.api.tecnicasradiologicas.model.Category;
import com.api.tecnicasradiologicas.repository.CategoryRepository;
import com.api.tecnicasradiologicas.repository.TechniqueRepository;
import com.api.tecnicasradiologicas.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TechniqueRepository techniqueRepository;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        return categoryService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/with-techniques")
    public ResponseEntity<List<Category>> getCategoriesWithTechniques() {
        List<Category> allCategories = categoryRepository.findAll();
        List<Category> categoriesWithTechniques = allCategories.stream()
                .filter(category -> techniqueRepository.existsByCategory(category))
                .toList();

        return ResponseEntity.ok(categoriesWithTechniques);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<CategoryDTO>> createMultiple(@RequestBody List<CategoryDTO> dtos) {
        return ResponseEntity.ok(categoryService.createMultiple(dtos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return categoryService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = categoryService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
