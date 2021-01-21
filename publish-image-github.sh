#!/bin/bash

# Tag the Docker image.
docker tag gamify-openjdk ghcr.io/heig-amt/gamify-openjdk

# Log in to GitHub package registry.
echo $TOKEN_GITHUB | docker login ghcr.io -u heig-AMT-bot --password-stdin

# Push the Docker image.
docker push ghcr.io/heig-amt/gamify-openjdk