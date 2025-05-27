<h1>Spring Boot REST application</h1>

<h2>Summary</h2>
<p>
Application is implemented following REST API architectural style using Spring Boot Web project.
Data is persisted in MySQL RDBMS and interaction with it is done via Hibernate ORM using
Spring Boot Data JPA project. Spring Security framework along with JWT are used for authentication and authorization of endpoints.
Project is developed on multiple branches using Git as version control system.
Application is containerized using Docker and image is pushed to repository in DockerHub for further usage during deployment.
For improved code quality and automation of build, test and DockerHub image upload processes is used GitHub Actions
where CI pipelines are run for different branches when code is commited. AWS is used for 
deployemnt(additional information <a href="./aws_cdk/README.md">here</a>) of the application image from DockerHub.
Kubernetes cluster is used in development environment to run containerized application
so that it can be scaled when needed. In future for deployment of the application in production environment can be
used Amazon Elastic Kubernetes(EKS). 
</p>

<h2>Tech stack</h2>
<ul>
    <li>Java 17</li>
    <li>Spring Boot 3+</li>
    <li>Spring Security 6</li>
    <li>MySQL</li>
    <li>Docker</li>
    <li>AWS</li>
    <li>Git</li>
    <li>Kubernetes</li>
    <li>CI with GitHub Actions</li>
    <li>React JS</li>
</ul> 