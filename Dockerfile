# Estágio de build
FROM eclipse-temurin:21-jdk-jammy AS build

# Instala dependências básicas para o GraalVM
RUN apt-get update && \
    apt-get install -y curl gcc zlib1g-dev build-essential && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Baixa e instala o GraalVM
RUN curl -L https://github.com/graalvm/graalvm-ce-builds/releases/download/jdk-21.0.2/graalvm-community-jdk-21.0.2_linux-x64_bin.tar.gz -o /tmp/graalvm.tar.gz && \
    echo "Verificando integridade do arquivo..." && \
    tar -tf /tmp/graalvm.tar.gz > /dev/null && \
    mkdir -p /opt/graalvm && \
    tar -xzf /tmp/graalvm.tar.gz -C /opt/graalvm --strip-components=1 && \
    rm /tmp/graalvm.tar.gz

# Configura o GraalVM como padrão
ENV GRAALVM_HOME=/opt/graalvm
ENV PATH="$GRAALVM_HOME/bin:$PATH"

# Copia o código fonte
COPY gradlew /usr/src/app/
COPY gradle /usr/src/app/gradle
COPY build.gradle settings.gradle /usr/src/app/
COPY src /usr/src/app/src

WORKDIR /usr/src/app

# Torna o gradlew executável e roda o build
RUN chmod +x gradlew && \
    ./gradlew build -Dquarkus.package.type=native

# Estágio final
FROM eclipse-temurin:21-jre-jammy

# Copia o executável nativo
COPY --from=build /usr/src/app/build/*-runner /application

EXPOSE 8080

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]