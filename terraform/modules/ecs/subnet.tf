resource "aws_subnet" "spring" {
  vpc_id                  = aws_vpc.main.id  # Link to your VPC
  cidr_block              = "10.0.0.0/28"    # 16 IPs for Spring app
  map_public_ip_on_launch = true              # Spring app gets public IP

  tags = {
    Name = "spring-subnet"
  }
}

resource "aws_subnet" "database" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "10.0.0.16/28"                # 16 IPs for DB
  # No public IP → private subnet

  tags = {
    Name = "database-subnet"
  }
}

