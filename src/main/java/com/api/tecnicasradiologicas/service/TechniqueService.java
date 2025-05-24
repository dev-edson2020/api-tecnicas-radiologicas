package com.api.tecnicasradiologicas.service;

import com.api.tecnicasradiologicas.dto.TechniqueDTO;
import com.api.tecnicasradiologicas.model.Technique;
import com.api.tecnicasradiologicas.repository.TechniqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechniqueService {

    private final TechniqueRepository techniqueRepository;

    public TechniqueService(TechniqueRepository techniqueRepository) {
        this.techniqueRepository = techniqueRepository;
    }

    public List<Technique> findAll() {
        return techniqueRepository.findAll();
    }

    public Optional<Technique> findById(String id) {
        return techniqueRepository.findById(id);
    }

    public Technique save(Technique technique) {
        return techniqueRepository.save(technique);
    }

    public void delete(Technique technique) {
        techniqueRepository.delete(technique);
    }

    public Technique convertDtoToEntity(TechniqueDTO dto) {
        Technique technique = new Technique();
        technique.setId(dto.getId());
        technique.setName(dto.getName());
        technique.setkV(dto.getkV());
        technique.setmAs(dto.getmAs());
        technique.setmA(dto.getmA());
        technique.setDistance(dto.getDistance());
        technique.setFullName(dto.getFullName());
        // Category será setada no controller, já que precisa ser carregada do DB
        return technique;
    }

    public void saveAll(List<TechniqueDTO> dtos) {
        List<Technique> techniques = dtos.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
        techniqueRepository.saveAll(techniques);
    }
}
