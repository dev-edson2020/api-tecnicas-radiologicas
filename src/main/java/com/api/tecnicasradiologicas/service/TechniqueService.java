package com.api.tecnicasradiologicas.service;

import com.api.tecnicasradiologicas.model.Technique;
import com.api.tecnicasradiologicas.repository.TechniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechniqueService {

    @Autowired
    private TechniqueRepository techniqueRepository;

    public List<Technique> findAll() {
        return techniqueRepository.findAll();
    }

    public Technique findById(Long id) {
        return techniqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technique not found"));
    }

    public Technique save(Technique technique) {
        return techniqueRepository.save(technique);
    }

    public Technique update(Long id, Technique technique) {
        Technique existing = findById(id);
        existing.setName(technique.getName());
        existing.setKv(technique.getKv());
        existing.setMas(technique.getMas());
        existing.setMa(technique.getMa());
        existing.setDistance(technique.getDistance());
        existing.setCategory(technique.getCategory());
        return techniqueRepository.save(existing);
    }

    public void delete(Long id) {
        techniqueRepository.deleteById(id);
    }
}
