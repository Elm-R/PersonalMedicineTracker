resource "aws_cloudwatch_dashboard" "meds_expiry_dashboard" {
  dashboard_name = "MedicineExpiryDays"

  dashboard_body = <<EOF
{
    "widgets": [
        {
            "type": "metric",
            "x": 0,
            "y": 0,
            "width": 6,
            "height": 6,
            "properties": {
                "metrics": [
                    [ { "expression": "SEARCH('{MedicineTrackerSpring,id,name,dosage} MetricName=\"MedicineExpiryDays\"', 'Average')", "label": "Expression1", "id": "e1", "period": 86400, "region": "eu-west-1" } ]
                ],
                "view": "bar",
                "stacked": false,
                "setPeriodToTimeRange": true,
                "region": "eu-west-1",
                "stat": "Average",
                "period": 86400
            }
        }
    ]
}

EOF
}
