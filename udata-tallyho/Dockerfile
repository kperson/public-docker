FROM kperson/ubuntu-java-7:latest

MAINTAINER Kelton Person <https://github.com/kperson>

#Server Code
EXPOSE 8080
WORKDIR /
ADD . /code
WORKDIR /code
RUN sbt 'project hub' clean compile stage
ENTRYPOINT ["hub/target/universal/stage/bin/hub", "--host", "0.0.0.0", "--port", "8080", "--bucket"]
