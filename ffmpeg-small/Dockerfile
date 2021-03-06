FROM alpine:3.8

ENV FFMPEG_VERSION=4.0.1

RUN apk add --update build-base curl nasm tar bzip2 \
  zlib-dev yasm-dev x264-dev rtmpdump-dev && \
  DIR=$(mktemp -d) && cd ${DIR} && \
  curl -s https://ffmpeg.org/releases/ffmpeg-${FFMPEG_VERSION}.tar.gz | tar zxvf - -C . && \
  cd ffmpeg-${FFMPEG_VERSION} && \
  ./configure \
    --enable-version3 --enable-gpl --enable-small --enable-avresample --disable-bsfs --disable-devices --disable-hwaccels --disable-filters --disable-demuxers --disable-decoders --disable-muxers --disable-encoders \
    --enable-demuxer=aac --enable-demuxer=h264 --enable-demuxer=flv --enable-demuxer=live_flv \
    --enable-muxer=h264 --enable-muxer=flv \
    --enable-decoder=aac --enable-decoder=h264 --enable-decoder=flv \ 
    --enable-encoder=aac --enable-encoder=libx264 --enable-encoder=flv \ 
    --enable-libx264 \
    --enable-librtmp \
    --enable-filter=scale --enable-filter=aresample \
    --disable-protocol=concat --disable-protocol=data --disable-protocol=crypto --disable-protocol=data \
    --disable-protocol=ftp --disable-protocol=gopher --disable-protocol=hls --disable-protocol=http \
    --disable-protocol=httpproxy --disable-protocol=mmst --disable-protocol=pipe --disable-protocol=rtp \
    --disable-protocol=subfile --disable-protocol=udp --disable-protocol=udplite --disable-protocol=unix \
    --disable-protocol=cache --disable-protocol=md5 --disable-protocol=async \
    --disable-protocol=srtp --disable-protocol=icecast --disable-protocol=prompeg --disable-protocol=tee && \
  make && \
  make install && \
  make distclean && \
  rm -rf ${DIR} && \
  apk del build-base curl tar bzip2 x264 nasm && rm -rf /var/cache/apk/*

COPY entrypoint.sh /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]