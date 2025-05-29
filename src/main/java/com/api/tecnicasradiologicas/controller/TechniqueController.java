package com.api.tecnicasradiologicas.controller;

import com.api.tecnicasradiologicas.model.Category;
import com.api.tecnicasradiologicas.model.Technique;
import com.api.tecnicasradiologicas.repository.CategoryRepository;
import com.api.tecnicasradiologicas.repository.TechniqueRepository;
import com.api.tecnicasradiologicas.service.TechniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/techniques")
@CrossOrigin(origins = "*")
public class TechniqueController {

    @Autowired
    private TechniqueService techniqueService;

    @Autowired
    private TechniqueRepository techniqueRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Technique> getAll() {
        return techniqueService.findAll();
    }

    @GetMapping("/{id}")
    public Technique getById(@PathVariable Long id) {
        return techniqueService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Technique> create(@RequestBody Technique technique) {
        if (technique.getMas() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Technique saved = techniqueService.save(technique);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public Technique update(@PathVariable Long id, @RequestBody Technique technique) {
        return techniqueService.update(id, technique);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnique(@PathVariable Long id) {
        Optional<Technique> techniqueOpt = techniqueRepository.findById(id);
        if (techniqueOpt.isPresent()) {
            Technique technique = techniqueOpt.get();
            Category category = technique.getCategory(); // obtém a categoria associada

            techniqueRepository.deleteById(id);

            // Verifica se ainda há alguma técnica usando essa categoria
            boolean categoryStillUsed = techniqueRepository.existsByCategory(category);
            if (!categoryStillUsed && category != null) {
                Optional<Category> categoryOpt = categoryRepository.findByName(category.getName());
                categoryOpt.ifPresent(categoryRepository::delete); // exclui com segurança a entidade Category
            }

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
