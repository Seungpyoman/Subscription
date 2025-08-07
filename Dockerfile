# 1. OpenJDK 21 slim 이미지를 기반으로 함
FROM openjdk:21-jdk-slim

# 2. JAR 파일을 컨테이너 내에 복사
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# 3. 애플리케이션을 실행하는 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]
