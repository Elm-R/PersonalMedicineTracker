provider "aws" {
# env variables are used for credentials
}

module "s3" {
  source = "./modules/s3"
}