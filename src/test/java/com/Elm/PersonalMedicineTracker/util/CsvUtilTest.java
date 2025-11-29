package com.Elm.PersonalMedicineTracker.util;

import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CsvUtilTest {

    @Test
    void testCsvGeneration() throws Exception {

        // Sample test data
        MedicineInventoryEntity med = new MedicineInventoryEntity();
        med.setId(1);
        med.setMedicineName("Paracetamol");
        med.setMedicineType("Tablet");
        med.setDosage("500mg");
        med.setQuantityInPackages(2);
        med.setUsageInstructions("Take 1 pill, after meal");
        med.setAddedOn(LocalDateTime.now());
        med.setExpiryDate(LocalDate.now().plusMonths(6));

        List<MedicineInventoryEntity> list = List.of(med);

        // Generate CSV content
        String csv = CsvUtil.generateCsv(list);

        // Write to a temporary file
        Path tempFile = Files.createTempFile("meds-", ".csv");
        try (FileWriter writer = new FileWriter(tempFile.toFile())) {
            writer.write(csv);
        }

        // Print file location for manual inspection
        System.out.println("CSV test file created at: " + tempFile.toAbsolutePath());
    }


}
