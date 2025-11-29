package com.Elm.PersonalMedicineTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Elm.PersonalMedicineTracker.util.CsvUtil;
import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import com.Elm.PersonalMedicineTracker.repository.MedicineInventoryRepository;

import java.util.List;

@Service
public class CsvExportService {

    private final MedicineInventoryRepository medicineRepository;
    private final CsvUtil csvUtil;

    public CsvExportService(MedicineInventoryRepository medicineRepository, CsvUtil csvUtil) {
        this.medicineRepository = medicineRepository;
        this.csvUtil = csvUtil;
    }

    /**
     * Fetches data from DB and generates CSV string.
     */
    public String exportMedicinesToCsv() {
        List<MedicineInventoryEntity> medicines = medicineRepository.findAll();
        return csvUtil.generateCsv(medicines);
    }

    public byte[] exportMedicinesToCsvBytes() {
        List<MedicineInventoryEntity> medicines = medicineRepository.findAll();
        return csvUtil.generateCsvAsBytes(medicines);  // uses the new helper
    }

}

