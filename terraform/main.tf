provider "aws" {
# env variables are used for credentials
}

module "iam" {
  source       = "./modules/iam"
  iam_username = var.iam_username
}

module "cloudwatch" {
  source       = "./modules/cloudwatch"
}

module "ecs" {
  source       = "./modules/ecs"
}
