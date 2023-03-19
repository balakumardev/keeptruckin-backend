FROM maven:3.8.3-openjdk-17 as buildstage
# Copy only pom.xml of your projects and download dependencies
COPY pom.xml .
RUN mvn -B -f pom.xml dependency:go-offline
# Copy all other project files and build project
COPY . .
RUN mvn -B install -DskipTests

#----
# Final stage
#----
FROM openjdk:17-oracle
COPY --from=buildstage ./target/*.jar ./
ENV JAVA_OPTS ""
CMD [ "bash", "-c", "java ${JAVA_OPTS} -jar *.jar -v"]