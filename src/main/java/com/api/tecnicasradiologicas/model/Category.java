package com.api.tecnicasradiologicas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    // Construtores
    public Category() {}

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
