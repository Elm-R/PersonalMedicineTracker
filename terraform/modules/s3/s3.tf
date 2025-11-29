#S3 bucket
resource "aws_s3_bucket" "medicines_inventory" {
  bucket = "medicines-inventory-spring"

  tags = {
    Name        = "SpringBoot Medicines Inventory Bucket"
    Environment = "Prod"
  }
}

#Enforce bucket ownership
resource "aws_s3_bucket_ownership_controls" "ownership_controls" {
  bucket = aws_s3_bucket.medicines_inventory.id

  rule {
    object_ownership = "BucketOwnerEnforced"
  }
}

#Block public access
resource "aws_s3_bucket_public_access_block" "block_public" {
  bucket = aws_s3_bucket.medicines_inventory.id

  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
}