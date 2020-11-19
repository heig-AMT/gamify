#!/bin/bash

# Tag the Docker image.
docker tag gamify-openjdk registry.heroku.com/heig-amt-gamify/web

# Log in to Heroku package registry.
echo $TOKEN_HEROKU | docker login registry.heroku.com --username=_ --password-stdin

# Push the Docker image.
docker push registry.heroku.com/heig-amt-gamify/web