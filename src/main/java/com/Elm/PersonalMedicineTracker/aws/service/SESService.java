package com.Elm.PersonalMedicineTracker.aws.service;

import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import com.Elm.PersonalMedicineTracker.service.MedicineInventoryService;
import com.Elm.PersonalMedicineTracker.util.CsvUtil;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SESService {

    private final SesClient sesClient;
    private final MedicineInventoryService medicineInventoryService;


    private static final String FROM = System.getenv("SENDER_EMAIL");

    private static final String[] TO_ADDRESSES =System.getenv("RECIPIENT_EMAIL").split(",");


    private static final String BODY = "You have Medicines Nearing Expiry in 10 days";
    private static final String SUBJECT = "Medicines Expiring in 10 days";



    public SESService(SesClient sesClient, MedicineInventoryService medicineInventoryService) {
        this.sesClient = sesClient;
        this.medicineInventoryService = medicineInventoryService;
    }

    // list of medicines that are expiring in 10 days
    public List<MedicineInventoryEntity> listOfExpiringMeds(int days) {

        return medicineInventoryService.getExpiringInDays(days);
    }

    public String listOfExpiringMedsCSV(int days) {
        List<MedicineInventoryEntity> meds = listOfExpiringMeds(days);
        return CsvUtil.generateCsv(meds);
    }

    private Destination buildDestination() {
        return Destination.builder()
                .toAddresses(TO_ADDRESSES)
                .build();
    }

    private Content buildSubject() {
        return Content.builder()
                .data(SUBJECT)
                .build();
    }

    private Body buildBody(int days) {
        String text = BODY + "\n\n" + listOfExpiringMedsCSV(days);
        return Body.builder()
                .text(Content.builder().data(text).build())
                .build();
    }

    private Message buildMessage(int days) {
        return Message.builder()
                .subject(buildSubject())
                .body(buildBody(days))
                .build();
    }

    private SendEmailRequest buildEmailRequest(int days) {
        return SendEmailRequest.builder()
                .source(FROM)
                .destination(buildDestination())
                .message(buildMessage(days))
                .build();
    }

    public void sendEmailMedsNearExp(int days) {
        SendEmailRequest request = buildEmailRequest(days);
        sesClient.sendEmail(request);
    }

}
