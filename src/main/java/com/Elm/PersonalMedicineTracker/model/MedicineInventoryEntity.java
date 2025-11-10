package com.Elm.PersonalMedicineTracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "medicines_inventory")
public class MedicineInventoryEntity {

    public MedicineInventoryEntity() {

    }

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "medicine_type")
    private String medicineType;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "quantity_in_packages")
    private Integer quantityInPackages;

    @Column(name = "usage_instructions")
    private String usageInstructions;

    @Column(name = "added_on")
    private LocalDateTime addedOn;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;


    // Setters

    public void setId(Integer id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setMedicineType(String medicineType) { this.medicineType = medicineType; }

    public void setDosage(String dosage) { this.dosage = dosage; }

    public void setQuantityInPackages(Integer quantityInPackages) { this.quantityInPackages = quantityInPackages; }

    public void setUsageInstructions(String usageInstructions) { this.usageInstructions = usageInstructions; }

    public void setAddedOn(LocalDateTime addedOn) { this.addedOn = addedOn; }

    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    // Getters
    public Integer getId() { return id; }

    public String getName() { return name; }

    public String getMedicineType() { return medicineType; }

    public String getDosage() { return dosage; }

    public Integer getQuantityInPackages() { return quantityInPackages; }

    public String getUsageInstructions() { return usageInstructions; }

    public LocalDateTime getAddedOn() { return addedOn; }

    public LocalDate getExpiryDate() { return expiryDate; }


}
