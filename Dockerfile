FROM java:8
MAINTAINER mlachmish@gmail.com
VOLUME /tmp
ADD build/libs/gift-service-core-0.1.0.jar app.jar
EXPOSE 443
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]