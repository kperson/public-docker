#!/bin/sh

# https://github.com/aws/aws-lambda-runtime-interface-emulator/
if [ -z "${AWS_LAMBDA_RUNTIME_API}" ]; then
    exec /usr/local/bin/aws-lambda-rie $DOCKER_EXECUTABLE "$@"
else
    exec $DOCKER_EXECUTABLE "$@"
fi