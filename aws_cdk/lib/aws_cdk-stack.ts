import * as cdk from 'aws-cdk-lib';
import { IpAddresses, Peer, SecurityGroup, Vpc } from 'aws-cdk-lib/aws-ec2';
import * as ecs from 'aws-cdk-lib/aws-ecs'
import * as ecsp from 'aws-cdk-lib/aws-ecs-patterns'

import { Construct } from 'constructs';
 

export class AwsCdkStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);
      const vpc = new Vpc(this, "cdk-vpc", {
        ipAddresses: IpAddresses.cidr("10.0.0.0/16")
      })

      const sg = new SecurityGroup(this, "cdk-security-group", {
          vpc,
          allowAllOutbound: true

      })
      // sg.connections.allowFrom(Peer.ipv4())
      new ecsp.ApplicationLoadBalancedFargateService(this, 'SpringBoot', {
        taskImageOptions: {
          image: ecs.ContainerImage.fromRegistry("knetsov91/spring-boot-rest:latest"),
          containerPort: 8080,

        
        }, 
        assignPublicIp:true,
        publicLoadBalancer: true
      }) 
  }
}
