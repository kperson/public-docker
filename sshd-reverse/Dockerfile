FROM alpine:3.5

RUN apk add --update openssh && rm -rf /tmp/* /var/cache/apk/*

RUN apk update && \
    apk add bash git openssh rsync && \
    mkdir -p ~root/.ssh /etc/authorized_keys && chmod 700 ~root/.ssh/ && \
    cp -a /etc/ssh /etc/ssh.cache && \
    rm -rf /var/cache/apk/*

ADD sshd_config /etc/ssh/sshd_config
ADD entrypoint.sh /entrypoint.sh

EXPOSE 22
ENTRYPOINT ["/entrypoint.sh"]
CMD ["/usr/sbin/sshd", "-D", "-f", "/etc/ssh/sshd_config"]
