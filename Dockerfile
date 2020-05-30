FROM maven:3.3-jdk-8 as builder
COPY ./pom.xml /code/
WORKDIR /code
RUN mvn verify clean --fail-never  dependency:go-offline dependency:resolve-plugins


COPY . /code/
RUN mvn package -DskipTests=true -B

FROM openjdk:8-jre
COPY --from=builder /code/target/*.jar /usr/app/
WORKDIR /usr/app

CMD ["exec", "java", "-jar", "practTema15-0.1.0-SNAPSHOT.jar"]
