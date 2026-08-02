resource "aws_ecs_service" "spring_service" {

  name            = "spring-service"
  cluster         = aws_ecs_cluster.main.id
  task_definition = aws_ecs_task_definition.medicine_tracker.arn

  desired_count = 1
  launch_type   = "FARGATE"

  network_configuration {

    subnets = [
      aws_subnet.spring.id
    ]

    security_groups = [
      aws_security_group.spring_sg.id
    ]

    assign_public_ip = true
  }
}