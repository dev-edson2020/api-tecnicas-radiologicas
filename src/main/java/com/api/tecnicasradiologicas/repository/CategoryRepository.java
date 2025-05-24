package com.api.tecnicasradiologicas.repository;

import com.api.tecnicasradiologicas.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
