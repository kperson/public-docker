FROM kperson/alpine-java-8

RUN apk add bash openssh git curl py2-pip==9.0.0-r1 --update

ADD https://download.docker.com/linux/static/stable/x86_64/docker-17.09.0-ce.tgz /docker-17.09.0-ce.tgz
ADD https://repo.jenkins-ci.org/releases/org/jenkins-ci/plugins/swarm-client/3.3/swarm-client-3.3.jar /swarm-client.jar

RUN mkdir -p /docker
RUN tar -zxvf /docker-17.09.0-ce.tgz -C /docker
RUN mv -v /docker/docker/* /usr/local/bin/
RUN rm -rf /docker/docker
RUN rm /docker-17.09.0-ce.tgz

ADD run-slave.sh /usr/local/bin

RUN pip install 'docker-compose==1.17.0'

RUN mkdir -p /root/.ssh

CMD ["run-slave.sh"]