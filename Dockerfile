# ===== 1) Build Stage =====
FROM gradle:8.7.0-jdk17 AS build
WORKDIR /app
COPY . .

# 윈도우 CRLF 제거 + 실행권한 부여
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

# 데몬 끄고 빌드
RUN ./gradlew --no-daemon clean bootJar -x test

# ===== 2) Run Stage =====
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# Render가 PORT를 주입하므로 server.port를 반드시 지정
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -Djava.security.egd=file:/dev/./urandom"
EXPOSE 8080
ENTRYPOINT ["sh","-c","java -Dserver.port=${PORT} $JAVA_OPTS -jar app.jar"]
