#!/usr/bin/env bash
./config.sh
serviceName="employee-service"
BUILD_NUMBER=$1

echo "start build docker image..."
docker build -t ${serviceName}:${BUILD_NUMBER} .