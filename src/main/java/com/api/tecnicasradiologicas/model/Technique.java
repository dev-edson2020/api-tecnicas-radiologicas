package com.api.tecnicasradiologicas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "techniques")
public class Technique {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private double kV;
    private double mAs;
    private double mA;
    private double distance;

    @Column(name = "full_name")
    private String fullName;

    // Construtores
    public Technique() {}

    public Technique(String id, String name, Category category, double kV, double mAs, double mA, double distance, String fullName) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.kV = kV;
        this.mAs = mAs;
        this.mA = mA;
        this.distance = distance;
        this.fullName = fullName;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getkV() {
        return kV;
    }

    public void setkV(double kV) {
        this.kV = kV;
    }

    public double getmAs() {
        return mAs;
    }

    public void setmAs(double mAs) {
        this.mAs = mAs;
    }

    public double getmA() {
        return mA;
    }

    public void setmA(double mA) {
        this.mA = mA;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
