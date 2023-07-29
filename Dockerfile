FROM tomcat:11.0-jdk17
COPY target/ontherun.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]