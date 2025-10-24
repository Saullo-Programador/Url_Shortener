# 🔗 URL Shortener API

[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-brightgreen)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-Build-blue)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue)](https://www.docker.com/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)

> API RESTful desenvolvida com **Spring Boot 3**, seguindo boas práticas de arquitetura e organização em camadas.  
Permite encurtar URLs longas, redirecionar para o endereço original e listar todos os links criados.

---

## 🚀 Tecnologias Utilizadas

- ☕ **Java 17+**  
- 🌱 **Spring Boot 3**  
- 🧩 **Spring Web**  
- 🧠 **Jakarta Validation**  
- ⚙️ **Maven**  
- 🐳 **Docker**  
- 🗃️ **MongoDB** 

---

## 📂 Estrutura do Projeto

```
com.example.url_shortener
│
├── controller
│   └── UrlController.java
│
├── service
│   └── UrlService.java
│
├── repository
│   └── UrlRepository.java
│
├── entity
│   └── Url.java
│
├── dto
│   ├── UrlRequestDTO.java
│   └── UrlResponseDTO.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
│
└── util
    └── ApiResponse.java
```

---

## ⚙️ Configuração do Projeto

### 🔧 Clonar o repositório
```bash
git clone https://github.com/Saullo-Programador/Url_Shortener.git
cd Url_Shortener
```

### 🧱 Rodar a aplicação
```bash
./mvnw spring-boot:run
```

Ou execute diretamente no IntelliJ / VS Code a classe principal com `@SpringBootApplication`.

---


## 🧠 Endpoints da API

Base URL:
```
http://localhost:8080/url
```

---

### 🔗 Encurtar uma URL
**POST** `/shorten`

#### 📤 Request Body (JSON)
```json
{
  "originalUrl": "https://shortener-2b1a.onrender.com"
}
```

## 🧠 Boas Práticas Implementadas

✅ Arquitetura em camadas (**Controller → Service → Repository**)  
✅ DTOs de entrada e saída para manter o código limpo  
✅ Tratamento global de exceções (`@ControllerAdvice`)  
✅ Validação de campos com **Jakarta Validation**  
✅ Respostas padronizadas via `ApiResponse<T>`  
✅ Código modular e pronto para escalabilidade  
✅ Dockerfile para deploy facilitado  

---

## 🧩 Possíveis Melhorias Futuras

- 🔐 Autenticação com **JWT** para URLs privadas  
- 📈 Estatísticas de acesso (quantidade de cliques, data do último acesso, IPs, etc.)  
- 🧭 Expiração automática de URLs antigas  
- 🧪 Testes unitários com **JUnit 5 + Mockito**  
- 📚 Documentação da API com **Swagger / OpenAPI**  
- 🐳 Publicação automatizada via **Docker Compose / CI-CD**

---

## 👨‍💻 Autor

**Saullo Paulo Dantas Felipe**  
📍 Juazeiro do Norte - CE  
🎓 Estudante de Engenharia de Software  
💼 Desenvolvedor Mobile | Backend | Software Engineer  
🌐 [GitHub: Saullo-Programador](https://github.com/Saullo-Programador)
