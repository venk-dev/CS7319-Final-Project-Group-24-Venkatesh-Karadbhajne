# E-learning Microservice Application

* The E-learning application offers online learning platform.

  * This application is developed with Security (HTTPS + Basic Auth with hashed password).
  * Use "**mvn clean install**" to build the codebase and create a Dockerfile.
  * For running this application, Docker Daemon or Docker Desktop is needed.
  * Dockerfile has been added to create a docker image such that the application can be run as a standalone docker container.
  * Spring Security configuration has been added. Please check the source com.venk.org.tutorials.config directory.
  * Security configuration has been added in application.properties file.

* Rationale for choosing Layered REST architecture.
  * REST Layered Microservice architecture is efficient in terms of container-based deployment.
  * For layered architecture, it is efficient to plugin different SSL, Authentication modules/frameworks
  * Flexibility for on-premise and cloud deployments.
  * For pipe and filter architecture, it is difficult to manage data flow for microservice based application.
  * For pipe and filter architecture, scalability and durability become highly complex.

* HTTPS Enablement
    * Add below environment variables in the env file to be used with "docker run" command. For HTTPS, port 8443 is used.
    * In case, KEY_PASS value is not provided, KEYSTORE_PASS value is used instead. Both the values need to be the same in that case.
      > KEYSTORE=KEYSTORE_VALUE
      KEYSTORE_PASS=KEYSTORE_PASS_VALUE
      KEYSTORE_TYPE=KEYSTORE_TYPE_VALUE
      KEY_ALIAS=KEY_ALIAS_VALUE
      KEY_PASS=KEY_PASS_VALUE
      SERVER_PORT=8443
    * Run the docker image using env variable HTTPS_MODE with true value to enable HTTPS.
      > docker run -v <host_directory>:/spring-app-data -p 8443:8443 -e HTTPS_MODE=true --env-file <host_directory>/env.list -it <image_tag>

* Basic Authentication Enablement
    * Add below environment variables in the env file to be used with "docker run" command.
      > REQUEST_USERNAME=user1
      REQUEST_PASSWORD=hashed password
    * Run the docker image using env variable ENABLE_BASIC_AUTH with true value to enable Basic Authentication.
      > docker run -v <host_directory>:/spring-app-data -p 8080:8080 -e ENABLE_BASIC_AUTH=true --env-file <host_directory>/env.list -it <image_tag> 


* HTTPS + Basic Authentication Enablement
    * Run the docker image using env variable ENABLE_BASIC_AUTH with true value and env variable HTTPS_MODE with true value.
      > docker run -v <host_directory>:/spring-app-data -p 8443:8443 -e ENABLE_BASIC_AUTH=true -e HTTPS_MODE=true --env-file <host_directory>/env.list -it <image_tag>
 
> * System Execution
     * Run the below command with HTTPS and Authentication both enabled.
       > Please check the files added under **ssl-auth**
       > In case of any issue, any self-signed certificate can be used. 
       > docker run -v <host_directory>:/spring-app-data -p 8443:8443 -e HTTPS_MODE=true  -e ENABLE_BASIC_AUTH=true --env-file <host_directory>/env-auth-https.list -it e-learning:1.0-SNAPSHOT
       > host_directory is a directory on the host machine where the Docker image is run.