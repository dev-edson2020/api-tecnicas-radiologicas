package com.api.tecnicasradiologicas.controller;

import com.api.tecnicasradiologicas.dto.TechniqueDTO;
import com.api.tecnicasradiologicas.model.Category;
import com.api.tecnicasradiologicas.model.Technique;
import com.api.tecnicasradiologicas.repository.CategoryRepository;
import com.api.tecnicasradiologicas.repository.TechniqueRepository;
import com.api.tecnicasradiologicas.service.TechniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("techniques")
@CrossOrigin(origins = "*")
public class TechniqueController {

    private final TechniqueService techniqueService;
    private final CategoryRepository categoryRepository;
    private final TechniqueRepository techniqueRepository;

    @Autowired
    public TechniqueController(TechniqueService techniqueService, CategoryRepository categoryRepository,
                               TechniqueRepository techniqueRepository) {
        this.techniqueService = techniqueService;
        this.categoryRepository = categoryRepository;
        this.techniqueRepository = techniqueRepository;
    }

    @GetMapping
    public List<Technique> getAll() {
        return techniqueService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technique> getById(@PathVariable String id) {
        return techniqueService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TechniqueDTO dto) {
        if (dto.getCategory() == null || dto.getCategory().getId() == null) {
            return ResponseEntity.badRequest().body("Category ID must be provided");
        }

        Category category = categoryRepository.findById(dto.getCategory().getId())
                .orElse(null);

        if (category == null) {
            return ResponseEntity.badRequest().body("Category not found");
        }

        Technique technique = techniqueService.convertDtoToEntity(dto);
        technique.setCategory(category);

        Technique saved = techniqueService.save(technique);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/batch")
    public ResponseEntity<?> saveTechniquesBatch(@RequestBody List<TechniqueDTO> dtos) {
        for (TechniqueDTO dto : dtos) {
            if (dto.getCategory() == null || dto.getCategory().getId() == null) {
                return ResponseEntity.badRequest().body("Category ID must be provided in all techniques");
            }

            Category category = categoryRepository.findById(dto.getCategory().getId())
                    .orElse(null);

            if (category == null) {
                return ResponseEntity.badRequest().body("Category not found: " + dto.getCategory().getId());
            }

            Technique technique = techniqueService.convertDtoToEntity(dto);
            technique.setCategory(category);
            techniqueService.save(technique);
        }
        return ResponseEntity.ok("Técnicas salvas com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody TechniqueDTO dto) {
        return techniqueService.findById(id).map(existing -> {
            existing.setName(dto.getName());

            if (dto.getCategory() != null && dto.getCategory().getId() != null) {
                Category category = categoryRepository.findById(dto.getCategory().getId()).orElse(null);
                if (category != null) {
                    existing.setCategory(category);
                }
            }

            existing.setkV(dto.getkV());
            existing.setmAs(dto.getmAs());
            existing.setmA(dto.getmA());
            existing.setDistance(dto.getDistance());
            existing.setFullName(dto.getFullName());

            techniqueService.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return techniqueService.findById(id).map(existing -> {
            techniqueService.delete(existing);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
