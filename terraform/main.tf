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

  ecs_task_execution_role_arn = module.iam.ecs_task_execution_role_arn

  mysql_database      = var.mysql_database
  mysql_user          = var.mysql_user
  mysql_password      = var.mysql_password
  mysql_root_password = var.mysql_root_password
}
