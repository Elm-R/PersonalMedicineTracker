resource "aws_grafana_workspace" "graf_meds_expiry_workspace" {
  name                 = "MedicineMetricsWorkspace"
  account_access_type  = "CURRENT_ACCOUNT"

  authentication_providers = ["AWS_SSO"]

  # Required when providing your own IAM role
  permission_type = "SERVICE_MANAGED"

  # Enable CloudWatch as a data source
  data_sources = ["CLOUDWATCH"]

  # Grafana will assume this IAM role to read CloudWatch metrics
  role_arn = var.grafana_cloudwatch_role_arn
}

