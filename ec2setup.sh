#!/bin/bash

##################################################
# ec2setup.sh
# works for Amazon Linux 2
# this is for setting up ec2 with java, maven, git, docker, jenkins

# Written by Chris Yun
# source: https://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html
# source: Tim's 12/16/2020 second video
##################################################

# update system
sudo yum update -y

##################################################

# set up java
sudo yum install java-1.8.0-openjdk-devel -y

##################################################

# set up maven
sudo yum install maven -y

##################################################

# set up git
sudo yum install git -y

##################################################

# skipped tomcat

##################################################

# docker engine package
sudo amazon-linux-extras install docker -y

# Start Docker
sudo service docker start

# Add ec2-user to docker group to avoid using sudo
sudo usermod -a -G docker ec2-user

# need to logout of ec2 session then relog in.
# if your Host name is different, you need to change line 29
exit
ssh javafsec2

# this command should be able to run without sudo
docker info

##################################################

# commands for jenkins setup
sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat-stable/jenkins.repo

sudo rpm --import https://pkg.jenkins.io/redhat/jenkins.io.key

sudo yum install -y jenkins

sudo service jenkins start

export JAVA_HOME=/usr/lib/jvm/java-1.8.0

export JENKINS_HOME=/var/lib/jenkins

# jenkins password
sudo cat /var/lib/jenkins/secrets/initialAdminPassword