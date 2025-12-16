output "grafana_cloudwatch_role_arn" {
  value = aws_iam_role.grafana_cloudwatch_assume_role.arn
}