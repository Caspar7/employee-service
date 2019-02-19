#!/usr/bin/env bash
serviceName="employee-service"
BUILD_NUMBER=$2

#get rand port
randPort(){
    min=$1
    max=$(($2-$min+1))
    num=$(cat /dev/urandom | head -n 10 | cksum | awk -F ' ' '{print $1}')
    echo $(($num%$max+$min))
}

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

echo "load docker images ${serviceName}_${BUILD_NUMBER}.tar .."
docker load -i ${serviceName}_${BUILD_NUMBER}.tar

echo "deploy docker container..."
deployPort=$(randPort 10000 60000)
/opt/deploy-service/clear-service.sh ${serviceName}
/opt/deploy-service/docker-image.sh load ${serviceName} ${BUILD_NUMBER}
docker run --env env=${env} --env deployIp=${deployIp} --env deployPort=${deployPort} -it -d -p ${deployPort}:${deployPort} --name ${serviceName} ${serviceName}:${BUILD_NUMBER}
