FROM alpine:3.5

#install dependencies
RUN apk update
RUN apk upgrade
RUN apk add --update ca-certificates wget
RUN update-ca-certificates
RUN apk add --update make gcc libc-dev pcre-dev openssl-dev

ADD nginx /tmp/nginx
WORKDIR /tmp/nginx
RUN ./configure
RUN make
RUN make install
RUN rm -rf /tmp/nginx

CMD ["/start.sh"]