package com.Elm.PersonalMedicineTracker.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class CsvExportServiceTest {

    @Autowired
    private CsvExportService csvExportService;


    @Test
    void generateCsvFromDb() throws Exception {
        // 1. Fetch data and generate CSV
        String csv = csvExportService.exportMedicinesToCsv();

        // 2. Save to a file in project root
        Path projectRootFile = Paths.get("medicinesInv.csv"); // project root
        try (FileWriter fw = new FileWriter(projectRootFile.toFile())) {
            fw.write(csv);
        }

        System.out.println("CSV generated at: " + projectRootFile.toAbsolutePath());
    }

}
