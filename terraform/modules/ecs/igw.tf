resource "aws_internet_gateway" "main" {
  vpc_id = aws_vpc.main.id  # Attach this IGW to your VPC

  tags = {
    Name = "main-igw"
  }
}