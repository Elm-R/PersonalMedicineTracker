package com.Elm.PersonalMedicineTracker.util;

import com.Elm.PersonalMedicineTracker.model.MedicineInventoryEntity;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;
@Component
public class CsvUtil {

    private static final String CSV_HEADER = "id,medicine_name," +
            "medicine_type,dosage," +
            "quantity_in_packages,usage_instructions," +
            "added_on,expiry_date\n";

    private CsvUtil() {}


    public static String generateCsv(List<MedicineInventoryEntity> medicines) {
        StringBuilder sb = new StringBuilder();
        appendHeader(sb);
        appendRows(sb, medicines);
        return sb.toString();
    }


    private static void appendHeader(StringBuilder sb) {
        sb.append(CSV_HEADER);
    }

    private static void appendRows(StringBuilder sb, List<MedicineInventoryEntity> medicines) {
        for (MedicineInventoryEntity med : medicines) {
            appendSingleRow(sb, med);
        }
    }


    private static void appendSingleRow(StringBuilder sb, MedicineInventoryEntity m) {
        sb.append(m.getId()).append(",");
        sb.append(escape(m.getMedicineName())).append(",");
        sb.append(escape(m.getMedicineType())).append(",");
        sb.append(escape(m.getDosage())).append(",");
        sb.append(m.getQuantityInPackages()).append(",");
        sb.append(escape(m.getUsageInstructions())).append(",");
        sb.append(m.getAddedOn()).append(",");
        sb.append(m.getExpiryDate()).append("\n");
    }


    private static String escape(String val) {
        if (val == null) return "";
        if (val.contains(",") || val.contains("\"") || val.contains("\n")) {
            return "\"" + val.replace("\"", "\"\"") + "\"";
        }
        return val;
    }

    public static byte[] convertCSVToBytes(String csvContent) {
        return csvContent.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] generateCsvAsBytes(List<MedicineInventoryEntity> medicines) {
        String csv = generateCsv(medicines);
        return convertCSVToBytes(csv);
    }

}
