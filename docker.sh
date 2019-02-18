#!/usr/bin/env bash

BUILD_NUMBER=$1
serviceName="employee-service"
images_path="/home/docker/images/"

echo "stop and delete exist docker images and container..."
running=`docker ps | grep ${serviceName} | awk '{print $1}'`
if [ ! -z "$running" ]; then
    docker stop $running
fi

container=`docker ps -a | grep ${serviceName} | awk '{print $1}'`
if [ ! -z "$container" ]; then
    docker rm $container -f
fi

imagesid=`docker images|grep -i ${serviceName}|awk '{print $3}'`
if [ ! -z "$imagesid" ]; then
    docker rmi $imagesid -f
fi

echo "start build docker image..."
docker build -t ${serviceName}:${BUILD_NUMBER} .

echo "delete old image file"
rm ${images_path}${serviceName}_*

echo "save images to" ${images_path}
docker save -o ${images_path}${serviceName}_${BUILD_NUMBER}.tar ${serviceName}:${BUILD_NUMBER}
#docker load -i ${serviceName}_${BUILD_NUMBER}.tar
