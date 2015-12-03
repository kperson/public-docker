FROM ubuntu:14.04

MAINTAINER Kelton Person <https://github.com/kperson>

ENV SWIFT_VERSION 2.2-SNAPSHOT-2015-12-01-b
ENV SWIFT_PLATFORM ubuntu14.04

RUN apt-get update
RUN apt-get install -y python-software-properties software-properties-common vim git wget libfreetype6 libfontconfig bzip2 build-essential libedit-dev libicu-dev clang
RUN apt-get upgrade -y

RUN mkdir /swift
WORKDIR /swift

RUN wget https://swift.org/builds/ubuntu1404/swift-${SWIFT_VERSION}/swift-${SWIFT_VERSION}-${SWIFT_PLATFORM}.tar.gz
RUN wget https://swift.org/builds/ubuntu1404/swift-${SWIFT_VERSION}/swift-${SWIFT_VERSION}-${SWIFT_PLATFORM}.tar.gz.sig
RUN wget -q -O - https://swift.org/keys/all-keys.asc | gpg --import -
RUN gpg --verify swift-${SWIFT_VERSION}-${SWIFT_PLATFORM}.tar.gz.sig
RUN tar xzf swift-${SWIFT_VERSION}-${SWIFT_PLATFORM}.tar.gz

ENV PATH /swift/swift-${SWIFT_VERSION}-${SWIFT_PLATFORM}/usr/bin:$PATH
RUN rm swift-${SWIFT_VERSION}-${SWIFT_PLATFORM}.tar.gz
RUN rm swift-${SWIFT_VERSION}-${SWIFT_PLATFORM}.tar.gz.sig

WORKDIR /
ENTRYPOINT ["bash"]
