# Use uma imagem base do Maven para compilação
FROM maven:3.8.4-openjdk-17-slim AS build

# Configuração do diretório de trabalho
WORKDIR /app

# Copia o arquivo POM e os arquivos de origem
COPY pom.xml .
COPY src ./src

# Compilação e empacotamento do aplicativo
RUN mvn clean package

# Imagem base para execução
FROM openjdk:17-jdk-alpine

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o JAR gerado durante a compilação
COPY --from=build /app/target/cardapio.war cardapio.war

# Expose the port used by the Spring Boot application
EXPOSE 8080

# Comando para iniciar a aplicação Spring Boot ao iniciar o contêiner
CMD ["java", "-jar", "cardapio.war"]