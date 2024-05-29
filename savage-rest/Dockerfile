# 指定基础镜像作为该容器的基础环境，如springboot应用最起码得有jdk环境
FROM openjdk:8

# 执行维护者的信息
MAINTAINER kaltsit

# 创建一个存放该工程的目录
RUN mkdir -p /java/project
# 设置容器时区为当前时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \&& echo 'Asia/Shanghai' >/etc/timezone
ADD target/savage-rest-1.0-SNAPSHOT.jar /java/project/savage-rest-1.0-SNAPSHOT.jar

# 对外暴露一个8899端口
EXPOSE 38834

# 执行启动
ENTRYPOINT ["/bin/sh", "-c", "java -jar /java/project/savage-rest-1.0-SNAPSHOT.jar --spring.profiles.active=prod"]
