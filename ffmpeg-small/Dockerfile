FROM alpine:3.8

ENV FFMPEG_VERSION=4.0.1

RUN apk add --update build-base curl nasm tar bzip2 \
  zlib-dev yasm-dev x264-dev rtmpdump-dev && \
  DIR=$(mktemp -d) && cd ${DIR} && \
  curl -s https://ffmpeg.org/releases/ffmpeg-${FFMPEG_VERSION}.tar.gz | tar zxvf - -C . && \
  cd ffmpeg-${FFMPEG_VERSION} && \
  ./configure \
  --enable-version3 --enable-gpl --enable-small --enable-libx264 --enable-librtmp --enable-avresample && \
  make && \
  make install && \
  make distclean && \
  rm -rf ${DIR} && \
  apk del build-base curl tar bzip2 x264 nasm && rm -rf /var/cache/apk/*

COPY entrypoint.sh /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]