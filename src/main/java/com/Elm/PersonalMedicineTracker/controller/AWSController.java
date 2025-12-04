package com.Elm.PersonalMedicineTracker.controller;
import com.Elm.PersonalMedicineTracker.aws.service.SESService;
import com.Elm.PersonalMedicineTracker.aws.service.S3Service;
import com.Elm.PersonalMedicineTracker.service.CsvExportService;

import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.ses.SesClient;


@RestController
@RequestMapping("/api/meds/aws/")
public class AWSController {

    private final CsvExportService csvExportService;
    private final S3Service s3Service;
    private final SESService sESService;

    public AWSController(CsvExportService csvExportService,
                         S3Service s3Service,
                         SESService sESService) {
        this.csvExportService = csvExportService;
        this.s3Service = s3Service;
        this.sESService= sESService;
    }



    @GetMapping("/upload")
    public String uploadCsvToS3(
            @RequestParam(defaultValue = "medicines-inventory-spring") String bucket,
            @RequestParam(defaultValue = "medicines_inventory.csv") String filename) {

        String csv = csvExportService.exportMedicinesToCsv();
        s3Service.uploadCsv(bucket, filename, csv);

        return "CSV uploaded successfully to bucket '" + bucket + "' as file '" + filename + "'!";
    }

    @GetMapping("/send-email/meds-near-exp/{days}")
    public String sendEmailMedsNearExpCont(@PathVariable Integer days){
        sESService.sendEmailMedsNearExp(days);
        return "Email sent for medicines expiring in " + days + " days!";
    }


}
