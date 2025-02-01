FROM eclipse-temurin:21-alpine

COPY /build/libs/*.jar /app/fleetat-importmap-mfe.jar
#COPY --from=builder /app/target/*.jar /app/app.jar

