# JDK sürümü
FROM openjdk:17

# Bilgilerndirme
LABEL maintainer="ilyascoskun200@gmail.com"

# Persist Data (Kalıcık Veri)
VOLUME /tmp

# Port
EXPOSE 4444

# Değişken
ARG JAR_FILE=/target/*.jar

# Değişkeni ekle
ADD ${JAR_FILE} docker_blog_react

ENTRYPOINT ["java","-jar","/docker_blog_react"]