resource "aws_iam_policy" "cw_put_metric_policy" {
  name        = "AllowPutCustomMetrics"
  description = "Allows pushing custom metrics to CloudWatch"


  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect   = "Allow"
        Action   = [
          "cloudwatch:PutMetricData",
          "cloudwatch:ListMetrics"
        ]
        Resource = "*"
      }
    ]
  })
}


