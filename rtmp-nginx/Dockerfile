FROM alpine:3.4

MAINTAINER https://github.com/kperson

#install dependencies
RUN apk update
RUN apk upgrade
RUN apk add --update make gcc libc-dev pcre-dev openssl-dev

#build and install nginx
# https://github.com/arut/nginx-rtmp-module
# http://nginx.org/download/

ADD nginx /tmp/nginx
ADD nginx-rtmp-module /tmp/nginx-rtmp-module
WORKDIR /tmp/nginx
RUN ./configure --add-module=/tmp/nginx-rtmp-module
RUN make
RUN make install
WORKDIR /
RUN rm -rf /tmp/nginx
RUN rm -rf /tmp/nginx-rtmp-module
RUN mkdir -p /etc/nginx

# install confd
ADD confd /usr/local/bin/confd
RUN mkdir -p /etc/confd/{conf.d,templates}

# configure confd templates/source
RUN mkdir -p /vout
ADD config.toml /etc/confd/conf.d/config.toml
ADD nginx.conf.tmpl /etc/confd/templates/nginx.conf.tmpl

VOLUME /vout
EXPOSE 80 1935

# entrypoint and command
ADD entrypoint.sh /entrypoint.sh
ADD start.sh /start.sh
ENTRYPOINT ["/entrypoint.sh"]
CMD ["/start.sh"]