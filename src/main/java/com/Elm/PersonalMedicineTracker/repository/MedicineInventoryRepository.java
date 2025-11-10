package com.Elm.PersonalMedicineTracker.repository;

import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineInventoryRepository extends JpaRepository<MedicineInventoryEntity, Integer> {


}

