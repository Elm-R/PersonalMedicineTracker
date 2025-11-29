package com.Elm.PersonalMedicineTracker.service;

import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import com.Elm.PersonalMedicineTracker.repository.MedicineInventoryRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals; // for assertEquals
import static org.mockito.Mockito.verify;                     // for verify
import static org.mockito.Mockito.when;                       // for when().thenReturn()
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MedicineInventoryServiceTest {

    @Mock
    private MedicineInventoryRepository medInvRepo;


    @Test
    void getExpiredMedicinesTest() {
        // 1. Fixed clock
        Clock fixedClock = Clock.fixed(Instant.parse("2025-01-01T00:00:00Z"),
                ZoneId.of("UTC"));

        // 2. Service with mocked repo
        MedicineInventoryService service = new MedicineInventoryService(medInvRepo, fixedClock);

        // 3. Mock repo return value
        List<MedicineInventoryEntity> expected = List.of(new MedicineInventoryEntity());
        when(medInvRepo.findExpiredMedicines(LocalDate.of(2025, 1, 1))).thenReturn(expected);

        // 4. Call service
        List<MedicineInventoryEntity> result = service.getExpiredMedicines();

        // 5. Assertions
        assertEquals(expected, result);
        verify(medInvRepo).findExpiredMedicines(LocalDate.of(2025, 1, 1));


    }

    @Test
    void getToExpireMedicinesTest() {
        // 1. Fixed clock
        Clock fixedClock = Clock.fixed(Instant.parse("2025-01-01T00:00:00Z"),
                ZoneId.of("UTC"));

        // 2. Service with mocked repo
        MedicineInventoryService service = new MedicineInventoryService(medInvRepo, fixedClock);

        // 3. Mock repo return value
        List<MedicineInventoryEntity> expected = List.of(new MedicineInventoryEntity());
        when(medInvRepo.findExpiringWithinDays
                (LocalDate.of(2025, 1, 1),
                        LocalDate.of(2025,     1, 11)
                )).thenReturn(expected);

        // 4. Call service
        List<MedicineInventoryEntity> result = service.getExpiringInDays(10);

        // 5. Assertions
        assertEquals(expected, result);
        verify(medInvRepo).findExpiringWithinDays(LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 11));

    }



}
