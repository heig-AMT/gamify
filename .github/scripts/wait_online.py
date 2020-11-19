#!/bin/env python3
import requests
import json
from time import sleep

server="http://localhost:8080/"

def start():
    try:
        sleep(5) # Sleep 5 seconds.
        res = requests.get(server)
        status = res.status_code
    except:
        status = 500
    return status

status = 400

while (status != 200):
    status = start()
