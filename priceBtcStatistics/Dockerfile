#FROM tomcat
#COPY target/priceBtcStatistics.war /usr/local/tomcat/webapps/
#EXPOSE 8080
FROM openjdk:11
ADD target/priceBtcStatistics.war priceBtcStatistics.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "priceBtcStatistics.war"]