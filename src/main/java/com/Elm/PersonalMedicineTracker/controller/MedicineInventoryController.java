package com.Elm.PersonalMedicineTracker.controller;

import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import com.Elm.PersonalMedicineTracker.repository.MedicineInventoryRepository;
import com.Elm.PersonalMedicineTracker.service.MedicineInventoryService;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/meds")
public class MedicineInventoryController {

    private final MedicineInventoryService medInvService;

    public MedicineInventoryController(MedicineInventoryService medInvService) {
        this.medInvService = medInvService;
    }

    //Get all medicines
    @GetMapping
    public List<MedicineInventoryEntity> getAllMedicines() {
        return medInvService.getAll();
    }

    //Get a single medicine by ID
    @GetMapping("/{id}")
    public MedicineInventoryEntity getMedicineById(@PathVariable Integer id) {
        return medInvService.getMedicineById(id);
    }

    // Update medicine quantity
    @PutMapping("/{id}/quantity")
    public ResponseEntity<String> updateQuantity(
            @PathVariable Integer id,
            @RequestParam Integer newQuantity) {

        medInvService.updateQuantity(id, newQuantity);
        return ResponseEntity.ok("Medicine with ID " + id + " updated successfully (new quantity: " + newQuantity + ").");
    }

    //Delete a medicine by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable Integer id) {
        medInvService.delete(id);
        return ResponseEntity.ok("Medicine with ID " + id + " deleted successfully.");
    }


}
