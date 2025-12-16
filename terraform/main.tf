provider "aws" {
# env variables are used for credentials
}

module "s3" {
  source = "./modules/s3"
}
module "iam" {
  source       = "./modules/iam"
  iam_username = var.iam_username
}

module "cloudwatch" {
  source       = "./modules/cloudwatch"
}

module "grafana" {
  source       = "./modules/grafana"
  grafana_cloudwatch_role_arn = module.iam.grafana_cloudwatch_role_arn
}