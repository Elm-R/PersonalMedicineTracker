package com.Elm.PersonalMedicineTracker.repository;

import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicineInventoryRepository extends JpaRepository<MedicineInventoryEntity, Integer> {

    // Already expired medicines
    @Query("SELECT m FROM MedicineInventoryEntity m WHERE m.expiryDate < :today")
    List<MedicineInventoryEntity> findExpiredMedicines(@Param("today") LocalDate today);

    // Medicines expiring within X days
    @Query("SELECT m FROM MedicineInventoryEntity m WHERE m.expiryDate BETWEEN :today AND :targetDate")
    List<MedicineInventoryEntity> findExpiringWithinDays(@Param("today") LocalDate today,
                                                         @Param("targetDate") LocalDate targetDate);


}

