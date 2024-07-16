#使用java环境，anapsix/alpine-java:latest java环境的镜像
FROM anapsix/alpine-java:latest
# 项目的端口，内部服务端口
EXPOSE 8080
# 切换到容器内部的 /workdir目录
WORKDIR /workdir

# 添加要运行的jar文件
COPY target/blog-backend-0.0.1-SNAPSHOT.jar /workdir/Blog.jar

# 容器启动后运行的命令
ENTRYPOINT ["java","-jar","/workdir/Blog.jar"]
