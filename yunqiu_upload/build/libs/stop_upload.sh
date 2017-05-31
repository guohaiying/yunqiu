#!/bin/sh   
PID=$(cat yunqiu_upload.pid)
echo $PID
kill -9 $PID