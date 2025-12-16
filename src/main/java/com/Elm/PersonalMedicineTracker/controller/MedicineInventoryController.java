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

    @PostMapping
    public MedicineInventoryEntity addMedicine(@RequestBody MedicineInventoryEntity medicine) {
        return medInvService.addMedicine(medicine);
    }

    //Get all medicines
    @GetMapping
    public List<MedicineInventoryEntity> getAllMedicines() {
        return medInvService.getAll();
    }

    //Get a single medicine by ID
    //@GetMapping("/{id}")
    @GetMapping("/{id:[0-9]+}")
    public MedicineInventoryEntity getMedicineById(@PathVariable Integer id) {
        return medInvService.getMedicineById(id);
    }

    // Update medicine quantity
    //@PutMapping("/{id}/quantity")
    @PutMapping("/{id:[0-9]+}/quantity")
    public ResponseEntity<String> updateQuantity(
            @PathVariable Integer id,
            @RequestParam Integer newQuantity) {

        medInvService.updateQuantity(id, newQuantity);
        return ResponseEntity.ok("Medicine with ID " + id + " updated successfully (new quantity: " + newQuantity + ").");
    }

    //Delete a medicine by ID
    //@DeleteMapping("/{id}")
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<String> deleteMedicine(@PathVariable Integer id) {
        medInvService.delete(id);
        return ResponseEntity.ok("Medicine with ID " + id + " deleted successfully.");
    }

    // GET /api/meds/NotExpired
    @GetMapping("/not-expired")
    public List<MedicineInventoryEntity> getNonExpiredMedicinesCont() {
        return medInvService.getNonExpiredMedicines();
    }

    // GET /api/meds/expired
    @GetMapping("/expired")
    public List<MedicineInventoryEntity> getExpiredMedicines() {
        return medInvService.getExpiredMedicines();
    }

    // GET /api/meds/expiring?days=7
    @GetMapping("/expiring")
    public List<MedicineInventoryEntity> getExpiringMedicines(@RequestParam int days) {
        return medInvService.getExpiringInDays(days);
    }

}
