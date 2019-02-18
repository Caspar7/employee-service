#!/usr/bin/env bash
BUILD_NUMBER=$1
env=$2
serviceName="employee-service"
images_path="/home/docker/images/"

#get rand port
randPort(){
    min=$1
    max=$(($2-$min+1))
    num=$(cat /dev/urandom | head -n 10 | cksum | awk -F ' ' '{print $1}')
    echo $(($num%$max+$min))
}

echo "scp docker image to target server"
src_dir=${images_path}${serviceName}_${BUILD_NUMBER}.tar
dest_dir=${images_path}
host=35.220.222.101
port=22
username=root
password=root


echo "deploy docker container..."
deployPort=$(randPort 10000 60000)


if [ "local" = "${env}" ];then
    docker run --env env=uat deployPort=${deployPort} -it -d -p ${deployPort}:${deployPort} --name ${serviceName} ${serviceName}:${BUILD_NUMBER}
    exit 0
fi

# scp docker file to remote server
#./expect_scp.sh $host $port $username $password $src_dir $dest_dir

#yum install sshpass if not install sshpass at build server
#sshpass -p $password ssh $username@${host} "docker load -i ${images_path}/${serviceName}_${BUILD_NUMBER}.tar"
#sshpass -p $password ssh $username@${host} "docker run --env env=${env} -it -d -p 2222:2222 --name ${serviceName} ${serviceName}:${BUILD_NUMBER}"

