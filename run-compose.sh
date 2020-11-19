#!/bin/sh
cd docker/topologies/integration
docker-compose down
docker-compose up --build --force-recreate --no-deps