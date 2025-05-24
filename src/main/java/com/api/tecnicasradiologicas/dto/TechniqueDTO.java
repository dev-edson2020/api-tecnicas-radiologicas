package com.api.tecnicasradiologicas.dto;

public class TechniqueDTO {
    private String id;
    private String name;
    private CategoryDTO category; // agora é objeto CategoryDTO
    private Integer kV;
    private Double mAs;
    private Integer mA;
    private Integer distance;
    private String fullName;

    // getters e setters para tudo, inclusive category

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

    public CategoryDTO getCategory() {
        return category;
    }
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public Integer getkV() {
        return kV;
    }
    public void setkV(Integer kV) {
        this.kV = kV;
    }

    public Double getmAs() {
        return mAs;
    }
    public void setmAs(Double mAs) {
        this.mAs = mAs;
    }

    public Integer getmA() {
        return mA;
    }
    public void setmA(Integer mA) {
        this.mA = mA;
    }

    public Integer getDistance() {
        return distance;
    }
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
