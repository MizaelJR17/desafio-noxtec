# desafio-noxtec

## Contato Controller API

Esta aplicação é uma API para gerenciar contatos, desenvolvida em **Java 17** com **Spring Boot** e utilizando **Maven** como ferramenta de gerenciamento de dependências. A aplicação inclui um frontend desenvolvido em Angular.

---

## **Requisitos**

### **Backend**
- Java 17
- Maven

### **Frontend**
- Node.js
- Angular CLI

---

## **Configurações de Inicialização**

### **Backend**

1. Clone o repositório e navegue até a pasta do projeto.
2. Execute os seguintes comandos:
   ```bash
   mvn clean install -U
   mvn spring-boot:run
   ```
3. A aplicação estará disponível em:
   `http://localhost:8080`

4. Para acessar o **Swagger UI** (documentação das rotas):
   - **URL**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  
   - **Credenciais**:
     ```properties
     spring.security.user.name=admin
     spring.security.user.password=123
     ```

---

### **Frontend**

1. Navegue até o diretório do frontend do projeto.
2. Instale o Angular CLI (caso ainda não esteja instalado):
   ```bash
   npm install -g @angular/cli@19
   ```
3. Instale as dependências do projeto:
   ```bash
   npm install
   ```
4. Execute o servidor de desenvolvimento:
   ```bash
   ng serve
   ```
5. Acesse o frontend em:  
   `http://localhost:4200`

![image](https://github.com/user-attachments/assets/dbf48f82-ba68-4e09-87b9-877cf409fa57)

---

## **Testes**

### **Backend**

Os testes podem ser executados utilizando **Maven**. Use os seguintes comandos:

- **Executar todos os testes**:
  ```bash
  mvn test
  ```
- **Executar testes unitários ou de integração específicos**:
  ```bash
  mvn -Dtest=ContatoControllerTest test
  mvn -Dtest=ContatoIntegrationTest test
  mvn -Dtest=ContatoServiceTest test
  ```

---

### **Frontend**

Para executar os testes do frontend (usando **Karma**):
```bash
ng test
```

---

## **Endpoints Disponíveis**
![image](https://github.com/user-attachments/assets/d068a520-937b-4381-99fe-4ba591743df7)


A API inclui os seguintes endpoints para gerenciar contatos (detalhados no Swagger):

- **PUT** `/api/v3/contatos/{id}`: Atualiza um contato existente.
- **PUT** `/api/v3/contatos/inativar/{id}`: Inativa um contato.
- **GET** `/api/v3/contatos`: Lista todos os contatos cadastrados.
- **POST** `/api/v3/contatos`: Cadastra um novo contato.

Para mais detalhes, consulte o **Swagger** em:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## **Observações**

- Certifique-se de que as credenciais para acessar o **Swagger UI** (`admin` / `123`) estejam configuradas corretamente no arquivo de propriedades do Spring Boot (`application.properties`).
- Utilize a versão correta do Angular CLI (`@angular/cli@19`) para evitar problemas de compatibilidade.

