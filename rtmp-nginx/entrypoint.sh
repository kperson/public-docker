#!/bin/sh

h=${HLS_ON:-true}
d=${DASH_ON:-true}

if [ $h == "true" ]; then
    export HLS=true
fi

if [ $d == "true" ]; then
    export DASH=true
fi

export STREAM=${STREAM_APP:-stream}
confd -onetime -backend env
cat /etc/nginx/nginx.conf
exec $@