FROM alpine:3.4

MAINTAINER https://github.com/kperson

ENV SCALA_VERSION=2.12.0
ENV SCALA_HOME=/usr/share/scala
ENV SBT_VERSION=0.13.13
ENV SBT_HOME=/usr/share/sbt

#install dependencies
RUN apk update
RUN apk upgrade
RUN apk add --update ca-certificates wget
RUN update-ca-certificates
RUN apk add openjdk8-jre-base bash
ENV JAVA_HOME /usr/lib/jvm/default-jvm

# scala
WORKDIR /usr/share
RUN wget -O scala.tgz http://downloads.lightbend.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.tgz 
RUN tar xvf scala.tgz
RUN mv scala-${SCALA_VERSION} scala
RUN ln -s "${SCALA_HOME}/bin/"* "/usr/bin/"

# sbt
RUN wget -O sbt.tgz https://dl.bintray.com/sbt/native-packages/sbt/${SBT_VERSION}/sbt-${SBT_VERSION}.tgz 
RUN tar xvf sbt.tgz
RUN mv sbt-launcher-packaging-${SBT_VERSION} sbt
RUN ln -s "${SBT_HOME}/bin/"* "/usr/bin/"

RUN rm scala.tgz
RUN rm sbt.tgz

WORKDIR /