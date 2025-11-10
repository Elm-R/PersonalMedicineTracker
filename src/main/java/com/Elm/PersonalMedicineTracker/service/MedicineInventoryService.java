package com.Elm.PersonalMedicineTracker.service;

import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import com.Elm.PersonalMedicineTracker.repository.MedicineInventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicineInventoryService {

    private final MedicineInventoryRepository medInvRepo;

    public MedicineInventoryService(MedicineInventoryRepository medInvRepo) {
        this.medInvRepo = medInvRepo;
    }

    public List<MedicineInventoryEntity> getAll() {
        return medInvRepo.findAll();
    }

    public MedicineInventoryEntity getMedicineById(Integer id) {
        return medInvRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
    }

    @Transactional
    public MedicineInventoryEntity updateQuantity(Integer id, Integer newQuantity) {
        MedicineInventoryEntity entity = medInvRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found"));
        entity.setQuantityInPackages(newQuantity);
        return medInvRepo.save(entity);
    }

    @Transactional
    public void delete(Integer id) {
        if (!medInvRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found");
        }
        medInvRepo.deleteById(id);
    }



}
