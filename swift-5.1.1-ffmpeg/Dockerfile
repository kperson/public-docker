FROM kperson/swift-5-dev

RUN apt-get update --fix-missing
RUN apt-get install -y libssl-dev libidn2-0 libnghttp2-14 libpsl-dev libunistring-dev libidn11 libgd-dev

RUN apt-get -y update --fix-missing
RUN apt -y install ffmpeg
ADD https://github.com/aws/aws-lambda-runtime-interface-emulator/releases/latest/download/aws-lambda-rie /usr/local/bin/aws-lambda-rie
RUN chmod +x /usr/local/bin/aws-lambda-rie

ADD test.sh /test.sh
RUN chmod +x /test.sh

ADD entry-point.sh /entry-point.sh
RUN chmod +x /entry-point.sh
ENTRYPOINT [ "/entry-point.sh" ]