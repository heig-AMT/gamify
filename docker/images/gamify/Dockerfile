FROM openjdk:11

LABEL maintainer="alexandre@piveteau.email"

WORKDIR /usr/gamify
ADD . .
ADD entrypoint.sh entrypoint.sh
ADD script.py script.py

USER root
RUN apt-get update
RUN apt-get --assume-yes install python3

ENTRYPOINT [ "./entrypoint.sh" ]