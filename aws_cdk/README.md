# AWS CDK Infrastructure

The application is deployed on AWS to provide a scalable and highly available production environment.
AWS CDK (TypeScript) is used to define and provision the infrastructure as code via CloudFormation,
allowing the entire stack to be created, updated, and destroyed with a single command.
The application image is pulled from DockerHub and run as a containerized service behind a public
load balancer, eliminating the need to manage underlying infrastructure.

## Architecture

- **VPC** — dedicated VPC with CIDR `10.0.0.0/16`
- **Security Group** — allows all outbound traffic
- **AWS Fargate** — serverless container engine running the application image from DockerHub (`knetsov91/spring-boot-rest:latest`)
- **Application Load Balancer (ALB)** — public-facing, routes external traffic to the Fargate container on port `8080`

## Usage

```bash
cd aws_cdk
npm install
cdk deploy
```

To destroy the stack:

```bash
cdk destroy
```
