resource "aws_ecs_task_definition" "medicine_tracker" {

  family = "medicine-tracker"

  network_mode = "awsvpc"

  requires_compatibilities = ["FARGATE"]

  cpu    = "1024"
  memory = "2048"

  execution_role_arn = var.ecs_task_execution_role_arn


  container_definitions = jsonencode([

    {
      name  = "spring-app"

      image = "elmri/personalmedicinetracker-springapp:latest"

      essential = true


      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
          protocol      = "tcp"
        }
      ]


      environment = [
        {
          name  = "MYSQL_DB_URL"
          value = "jdbc:mysql://localhost:3306/meds_tracker_db"
        },
        {
          name  = "MYSQL_USER"
          value = var.mysql_user
        },
        {
          name  = "MYSQL_PASSWORD"
          value = var.mysql_password
        }
      ]
    },


    {
      name  = "mysqldb"

      image = "elmri/personalmedicinetracker-mysqldb-service:latest"

      essential = true


      portMappings = [
        {
          containerPort = 3306
          hostPort      = 3306
          protocol      = "tcp"
        }
      ]


      environment = [
        {
          name  = "MYSQL_DATABASE"
          value = var.mysql_database
        },
        {
          name  = "MYSQL_USER"
          value = var.mysql_user
        },
        {
          name  = "MYSQL_PASSWORD"
          value = var.mysql_password
        },
        {
          name  = "MYSQL_ROOT_PASSWORD"
          value = var.mysql_root_password
        }
      ]
    }

  ])
}