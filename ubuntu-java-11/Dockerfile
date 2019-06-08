FROM ubuntu:18.04

MAINTAINER https://github.com/kperson

ENV SCALA_VERSION=2.12.8
ENV SCALA_HOME=/usr/share/scala
ENV SBT_VERSION=0.13.13
ENV SBT_HOME=/usr/share/sbt

#java
RUN apt-get update && \
    apt-get install -y software-properties-common zip wget && \
    add-apt-repository ppa:linuxuprising/java && \
    apt-get update && \
    echo oracle-java11-installer shared/accepted-oracle-license-v1-2 select true | debconf-set-selections && \
    apt-get install -y oracle-java11-installer && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /var/cache/oracle-jdk11-installer
ENV JAVA_HOME /usr/lib/jvm/java-11-oracle

# scala
WORKDIR /usr/share
RUN wget -O scala.tgz http://downloads.lightbend.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.tgz && \
    tar xvf scala.tgz && \
    mv scala-${SCALA_VERSION} scala && \
    rm scala.tgz
    
RUN ln -s "${SCALA_HOME}/bin/"* "/usr/bin/"

# sbt
RUN wget -O sbt.tgz https://dl.bintray.com/sbt/native-packages/sbt/${SBT_VERSION}/sbt-${SBT_VERSION}.tgz && \
    tar xvf sbt.tgz && \    
    mv sbt-launcher-packaging-${SBT_VERSION} sbt && \
    rm sbt.tgz

RUN ln -s "${SBT_HOME}/bin/"* "/usr/bin/"

WORKDIR /
