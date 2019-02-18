#!/usr/bin/env bash

BUILD_NUMBER=$1
env=$2
serviceName="employee-service"
images_path="/home/docker/images/"

echo "scp docker image to target server"

echo "load docker images to local"
#docker load -i ${images_path}/${serviceName}_${BUILD_NUMBER}.tar

echo "run container..."
docker run --env env=${env} -it -d -p 2222:2222 --name ${serviceName} ${serviceName}:${BUILD_NUMBER}