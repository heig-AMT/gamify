#!/bin/bash

# Maven
echo "Building Gamify"
cd gamify-impl
mvn -B clean package

# OpenJDK + Spring
echo "Building Gamify Docker image"
cp target/gamify-impl-0.1.0.jar ../docker/images/gamify
docker build -t gamify-openjdk ../docker/images/gamify