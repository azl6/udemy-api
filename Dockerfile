FROM openjdk:8

EXPOSE 8080

COPY target/gvendas-0.0.1-SNAPSHOT.jar /app/gestao_vendas.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", ""]