#!/bin/bash

cp /run/secrets/jenkins_id_rsa.pub /root/.ssh/id_rsa.pub
cp /run/secrets/jenkins_id_rsa /root/.ssh/id_rsa

mkdir -p /root/jenkins_fs
java -jar /swarm-client.jar -username $JENKINS_USERNAME -password $(cat /run/secrets/jenkins_password) -labels docker -name $(hostname -i) -master $JENKINS_MASTER -fsroot /root/jenkins_fs -deleteExistingClients
