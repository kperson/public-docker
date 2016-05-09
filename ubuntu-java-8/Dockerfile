FROM ubuntu:14.04

MAINTAINER Kelton Person <https://github.com/kperson>

#Base
RUN apt-get update
RUN apt-get install -y python-software-properties software-properties-common vim git wget libfreetype6 libfontconfig bzip2 build-essential
RUN apt-get upgrade -y
RUN apt-get install -y apparmor

#Java 8
RUN \
  echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

#SBT
RUN wget -O sbt.deb https://bintray.com/artifact/download/sbt/debian/sbt-0.13.9.deb
RUN dpkg -i sbt.deb
RUN rm sbt.deb
