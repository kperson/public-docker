FROM ubuntu

RUN apt-get update
RUN apt-get install -y python-software-properties software-properties-common
RUN apt-get install -y vim git wget libfreetype6 libfontconfig bzip2  build-essential
RUN apt-get upgrade -y

RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update
RUN echo oracle-java6-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN apt-get install oracle-java6-installer -y
ENV JAVA_HOME /usr/lib/jvm/java-6-oracle
RUN apt-get install -y ant
ENV ANT_HOME /usr/share/ant

WORKDIR /
RUN apt-get update
RUN apt-get install -y unzip wget make mercurial git bzr libsqlite3-dev sqlite3
RUN wget -q http://downloads.typesafe.com/play/1.2.7/play-1.2.7.zip
RUN unzip -q play-1.2.7.zip
RUN mv play-1.2.7 /opt/play
RUN rm play-1.2.7.zip

RUN apt-get install -y python

RUN useradd -m play
RUN chown -R play /opt/play
RUN chgrp -R play /opt/play
ENV PATH "$PATH:/opt/play"
RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*
