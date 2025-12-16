package com.Elm.PersonalMedicineTracker.aws.service;

import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import com.Elm.PersonalMedicineTracker.service.MedicineInventoryService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.Dimension;
import software.amazon.awssdk.services.cloudwatch.model.MetricDatum;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricDataRequest;
import software.amazon.awssdk.services.cloudwatch.model.StandardUnit;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CloudWatchService {

    private final CloudWatchClient cloudWatch;
    private final MedicineInventoryService medicineService;
    private final Clock clock;

    private static final String NAMESPACE = "MedicineTrackerSpring";

    public CloudWatchService(CloudWatchClient cloudWatch,
                             MedicineInventoryService medicineService,
                             Clock clock) {
        this.cloudWatch = cloudWatch;
        this.medicineService = medicineService;
        this.clock = clock;
    }

    // --- Build dimensions ---
    private List<Dimension> buildDimensions(MedicineInventoryEntity m, String listType) {
        return List.of(
                Dimension.builder().name("id").value(m.getId().toString()).build(),
                Dimension.builder().name("name").value(m.getMedicineName()).build(),
                Dimension.builder().name("dosage").value(m.getDosage()).build(),
                Dimension.builder().name("listType").value(listType).build()
        );
    }

    // --- Build metric ---
    private MetricDatum buildMetricDatum(String metricName, double value, List<Dimension> dimensions) {
        return MetricDatum.builder()
                .metricName(metricName)
                .value(value)
                .dimensions(dimensions)
                .unit(StandardUnit.NONE)
                .build();
    }

    // --- Push to CloudWatch ---
    private void pushMetric(String metricName, double value, List<Dimension> dimensions) {
        PutMetricDataRequest request = PutMetricDataRequest.builder()
                .namespace(NAMESPACE)
                .metricData(buildMetricDatum(metricName, value, dimensions))
                .build();

        cloudWatch.putMetricData(request);
    }

    // --- Generic metric pusher ---
    private void pushMetricsFor(List<MedicineInventoryEntity> meds, String listType) {
        LocalDate today = LocalDate.now(clock);

        for (MedicineInventoryEntity m : meds) {
            if (m.getExpiryDate() == null) continue;

            double daysUntilExpiry = ChronoUnit.DAYS.between(today, m.getExpiryDate());
            pushMetric("MedicineExpiryDays", daysUntilExpiry, buildDimensions(m, listType));
        }
    }

    // --- Public functions called by controller ---
    public void pushAllMedicineMetrics() {
        pushMetricsFor(medicineService.getAll(), "all");
    }

    public void pushNonExpiredMedicineMetrics() {
        pushMetricsFor(medicineService.getNonExpiredMedicines(), "non-expired");
    }
}
