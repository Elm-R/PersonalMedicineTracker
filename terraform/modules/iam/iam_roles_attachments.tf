resource "aws_iam_role_policy_attachment" "grafana_cw_policy_attachment" {
  role       = aws_iam_role.grafana_cloudwatch_assume_role.name
  policy_arn = "arn:aws:iam::aws:policy/CloudWatchReadOnlyAccess"
}