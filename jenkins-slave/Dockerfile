FROM kperson/alpine-java-8

RUN apk add bash openssh git curl py-pip==8.1.2-r0 --update

ADD https://get.docker.com/builds/Linux/x86_64/docker-1.13.1.tgz /docker-1.13.1.tgz
ADD https://repo.jenkins-ci.org/releases/org/jenkins-ci/plugins/swarm-client/3.3/swarm-client-3.3.jar /swarm-client.jar

RUN mkdir -p /docker
RUN tar -zxvf /docker-1.13.1.tgz -C /docker
RUN mv -v /docker/docker/* /usr/local/bin/
RUN rm -rf /docker/docker
RUN rm /docker-1.13.1.tgz

ADD run-slave.sh /usr/local/bin

RUN pip install 'docker-compose==1.11.2'

RUN mkdir -p /root/.ssh

CMD ["run-slave.sh"]
