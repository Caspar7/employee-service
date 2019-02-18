#!/usr/bin/env bash

BUILD_NUMBER=$1
serviceName="employee-service"
images_path="/home/docker/images/"

echo "start build docker image..."
docker build -t ${serviceName}:${BUILD_NUMBER} .

#echo "delete old image file"
#rm ${images_path}${serviceName}_*

echo "save images to" ${images_path}
docker save -o ${images_path}${serviceName}_${BUILD_NUMBER}.tar ${serviceName}:${BUILD_NUMBER}
#docker load -i ${serviceName}_${BUILD_NUMBER}.tar
