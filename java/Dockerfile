FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

# Instalar o Maven
RUN apk update && \
    apk add maven

# Copiar arquivos necessários para o diretório de trabalho
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Executar o comando Maven
RUN --mount=type=cache,target=/root/.m2 mvn install -DskipTests

# Criar diretório para dependências e extrair arquivos JAR
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

# Copiar dependências para a pasta de aplicativos
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Definir ponto de entrada para a aplicação Java
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.demo.DemoApplication"]
