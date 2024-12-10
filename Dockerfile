# ---------------------------
# Etapa 1: Build do projeto
# ---------------------------
FROM maven:3.9.4-eclipse-temurin-17 as builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o conteúdo do projeto para o diretório de trabalho
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

# Compila o projeto e empacota em um jar
RUN mvn clean package -DskipTests

# ---------------------------
# Etapa 2: Imagem final
# ---------------------------
FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho do runtime
WORKDIR /app

# Copia o jar gerado na etapa de build para a imagem final
COPY --from=builder /app/target/cinema-1.0-SNAPSHOT.jar app.jar

# Porta exposta pela aplicação
EXPOSE 8080

# Comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
