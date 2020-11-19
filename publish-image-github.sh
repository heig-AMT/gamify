#!/bin/bash

# Tag the Docker image.
docker tag gamify-openjdk docker.pkg.github.com/heig-amt/gamify/openjdk

# Log in to GitHub package registry.
echo $TOKEN_GITHUB | docker login https://docker.pkg.github.com -u heig-AMT-bot --password-stdin

# Push the Docker image.
docker push docker.pkg.github.com/heig-amt/gamify/openjdk