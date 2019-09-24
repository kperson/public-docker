FROM swift:5.0.1-xenial

# HINT: run ldd <binary_location> to find linking locations
# https://superuser.com/questions/239590/find-libraries-a-binary-was-linked-against
RUN apt-get update --fix-missing
RUN apt-get install -y libssl-dev libidn2-0 libnghttp2-14 libpsl-dev libunistring-dev libidn11 libgd-dev
