package com.Elm.PersonalMedicineTracker.controller;

import com.Elm.PersonalMedicineTracker.service.CsvExportService;
import com.Elm.PersonalMedicineTracker.aws.service.S3Service;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
@RestController
@RequestMapping("/api/meds/export")
public class MedicineExportController {

    private final CsvExportService csvExportService;
    private final S3Service s3Service;

    public MedicineExportController(CsvExportService csvExportService, S3Service s3Service) {
        this.csvExportService = csvExportService;
        this.s3Service = s3Service;
    }

    @GetMapping
    public ResponseEntity<Resource> exportCsv() {
        System.out.println("CSV export endpoint called!");
        String csvContent = csvExportService.exportMedicinesToCsv();
        System.out.println("CSV length: " + csvContent.length());
        System.out.println("First 200 chars:\n" + csvContent.substring(0, Math.min(200, csvContent.length())));

        ByteArrayResource resource = new ByteArrayResource(csvContent.getBytes(StandardCharsets.UTF_8));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=medicines.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(csvContent.getBytes().length)
                .body(resource);
    }

    @GetMapping("/upload")
    public String uploadCsvToS3(
            @RequestParam(defaultValue = "medicines-inventory-spring") String bucket,
            @RequestParam(defaultValue = "medicines_inventory.csv") String filename) {

        String csv = csvExportService.exportMedicinesToCsv();
        s3Service.uploadCsv(bucket, filename, csv);

        return "CSV uploaded successfully to bucket '" + bucket + "' as file '" + filename + "'!";
    }


}

