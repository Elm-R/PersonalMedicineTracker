resource "aws_iam_user_policy_attachment" "attach_policy" {
  user       = var.iam_username
  policy_arn = aws_iam_policy.cw_put_metric_policy.arn
}