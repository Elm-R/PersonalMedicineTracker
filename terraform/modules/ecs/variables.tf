variable "ecs_task_execution_role_arn" {
  type = string
}

variable "mysql_database" {
  description = "MySQL database name"
  type        = string
}

variable "mysql_user" {
  description = "MySQL application user"
  type        = string
  sensitive   = true
}

variable "mysql_password" {
  description = "MySQL application password"
  type        = string
  sensitive   = true
}

variable "mysql_root_password" {
  description = "MySQL root password"
  type        = string
  sensitive   = true
}