import subprocess, os, sys
from urllib.parse import urlparse

# Retrieve the current environment.
my_env = os.environ.copy()
my_url = urlparse(my_env["DATABASE_URL"])

# Prepare different variables in a JDBC + PostgreSQL-friendly format.
database_url = my_url.hostname + ":" + str(my_url.port)
database_name = my_url.path.replace("/", "", 1)
database_username = my_url.username
database_password = my_url.password

# Prepare the environment variables.
my_env["SPRING_DATASOURCE_URL"] = "jdbc:postgresql://" + database_url + "/" + database_name
my_env["SPRING_DATASOURCE_USERNAME"] = database_username
my_env["SPRING_DATASOURCE_PASSWORD"] = database_password

print("[LAUNCH] script.py")

# Open a subprocess with the new environment, pipe standard output and await termination.
subprocess.Popen(["java", "-jar", "gamify-impl-0.1.0.jar", "-XX:MaxRAM=100m"],
            env     = my_env,
            stdout  = sys.stdout,
            stderr  = sys.stderr).communicate()