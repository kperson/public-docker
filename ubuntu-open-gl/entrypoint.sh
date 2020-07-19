#!/bin/bash

if [ $# -eq 0 ]; then
	echo "No command was given to run, exiting."
	exit 1
else
	export XVFB_WHD=1920x1080x24
	export LIBGL_ALWAYS_SOFTWARE=1
	export GALLIUM_DRIVER=llvmpipe
	export LP_NO_RAST=false
	export LP_DEBUG=""
	export LP_PERF=""
	export LP_NUM_THREADS=""
	export DISPLAY=:99
	FILE=start.txt
	if [ -f "$FILE" ]; then
		PREV_MY_ID=$(cat $FILE)
		kill $PREV_MY_ID
	fi
	echo "Starting Xvfb"
	Xvfb :99 -ac -screen 0 "$XVFB_WHD" -nolisten tcp &
	MY_ID=$!
	sleep 2
	if [[ -n "$VNC_PASSWORD" ]]; then
		echo 'Starting VNC Server'
		x11vnc -storepasswd ${VNC_PASSWORD} passfile.txt
		x11vnc -rfbauth passfile.txt -display :99 &
	fi
	echo 'Running Primary Command'
	echo $MY_ID > $FILE
	$@
fi