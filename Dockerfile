FROM amazoncorretto:17-alpine

WORKDIR /app

COPY target/product-api-0.0.1-SNAPSHOT-exec.jar app.jar

EXPOSE 8086

HEALTHCHECK --interval=30s --timeout=5s --start-period=40s --retries=3 \
  CMD wget -qO- http://localhost:8086/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]