package com.Elm.PersonalMedicineTracker.service;

import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import com.Elm.PersonalMedicineTracker.repository.MedicineInventoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineInventoryService {

    private final MedicineInventoryRepository medInvRepo;
    private final Clock clock;

    public MedicineInventoryService(MedicineInventoryRepository medInvRepo, Clock clock) {
        this.medInvRepo = medInvRepo;
        this.clock = clock;
    }


    @Transactional
    public MedicineInventoryEntity addMedicine(MedicineInventoryEntity medicine) {
        // --- Validation ---

        if (medicine.getMedicineName() == null || medicine.getMedicineName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Medicine name is required");
        }

        if (medicine.getDosage() == null || medicine.getDosage().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dosage is required");
        }

        if (medicine.getMedicineType() == null || medicine.getMedicineType().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Medicine type is required");
        }

        if (medicine.getQuantityInPackages() == null || medicine.getQuantityInPackages() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be 0 or more");
        }

        if (medicine.getUsageInstructions() == null || medicine.getUsageInstructions().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usage instructions are required");
        }

        if (medicine.getExpiryDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expiry date is required");
        }

        // --- Set automatic fields ---
        medicine.setAddedOn(LocalDateTime.now());

        // --- Save to database ---
        return medInvRepo.save(medicine);
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

//    public List<MedicineInventoryEntity> getExpiredMedicines() {
//        return medInvRepo.findExpiredMedicines(LocalDate.now());
//    }

    public List<MedicineInventoryEntity> getExpiredMedicines() {
        return medInvRepo.findExpiredMedicines(LocalDate.now(clock));
    }

    public List<MedicineInventoryEntity> getExpiringInDays(int days) {
        LocalDate today = LocalDate.now(clock);
        LocalDate targetDate = today.plusDays(days);
        return medInvRepo.findExpiringWithinDays(today, targetDate);
    }

}
