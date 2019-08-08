## Code Challenge - Apache Tomcat DOCKER IMAGE
Return to Main:[README.md] (https://github.com/luisamesty/orangebanktest/blob/master/README.md)
## Docker Image Requirements
- Apache Tomcat
### Apache Tomcat
Version 8.5 
URL: https://hub.docker.com/_/tomcat
### Install Tomcat Image and Run Container
<pre>
Challenge Test has been tested on MAC OS High Sierra
Version 10.13.6. Using Kitematic.
Image can be installed manually or using the appropiate web or desktop application.
Once Image is dowloaded. Then Container must be run using appropiate parameters.
You can use two container for handlin both APPs separated with different IP.
Docker command requires:
    1. Full path of War file
    2. ExternalIP:LocalIP
    3. Image_Name
On Windows or Linux, take care about directories. 
Command sintax:
$ docker run -v LOCAL_PC_War_File_Path_And_Name:Container_War_Path_And_Name -it -p  ExternalIP:LocalIP  Image_Name
Command tested:
$ docker run -v /Volumes/Datos/Orange/OrangeCodeChallenge/OrangeBookApp/target/OrangeBookApp-0.0.1-SNAPSHOT.war:/usr/local/tomcat/webapps/myapp.war -it -p 32780:8080 tomcat
</pre>
Return to Main: [README.md](https://github.com/luisamesty/orangebanktest/blob/master/README.md)