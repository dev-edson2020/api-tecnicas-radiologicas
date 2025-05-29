package com.api.tecnicasradiologicas.repository;

import com.api.tecnicasradiologicas.model.Category;
import com.api.tecnicasradiologicas.model.Technique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechniqueRepository extends JpaRepository<Technique, Long> {
    boolean existsByCategory(Category category);
}
