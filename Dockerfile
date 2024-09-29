FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
RUN chmod +x gradlew
RUN ./gradlew build -x test --no-daemon || true
COPY . /app
RUN ./gradlew bootJar -x test --no-daemon
EXPOSE 8083 5005
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/build/libs/DonaldTaxesEntrprise-0.0.1-SNAPSHOT.jar"]