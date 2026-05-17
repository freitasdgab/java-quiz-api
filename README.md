# 🍵 Java Quiz API

API REST estilo Duolingo para aprender conceitos de Java.

## 🚀 Como rodar

```bash
mvn spring-boot:run
```

Acesse em: `http://localhost:8080`

Console H2 (banco em memória): `http://localhost:8080/h2-console`

---

## 📋 Endpoints

### Perguntas (Quiz)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/perguntas` | Lista todas as perguntas (sem gabarito) em ordem aleatória |
| GET | `/api/perguntas/{id}` | Busca uma pergunta pelo ID |
| GET | `/api/perguntas/nivel/{nivel}` | Filtra por nível: `INICIANTE`, `INTERMEDIARIO`, `AVANCADO` |
| GET | `/api/perguntas/tipo/{tipo}` | Filtra por tipo: `MULTIPLA_ESCOLHA`, `VERDADEIRO_FALSO`, `COMPLETAR_CODIGO` |
| GET | `/api/perguntas/categoria/{categoria}` | Filtra por categoria (ex: `OOP`, `SINTAXE`) |
| GET | `/api/perguntas/categorias` | Lista todas as categorias disponíveis |
| POST | `/api/perguntas/{id}/responder` | Verifica se a resposta está correta |

### Perguntas (Admin - com gabarito)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/perguntas/admin` | Lista todas com gabarito |
| POST | `/api/perguntas` | Cria nova pergunta |
| PUT | `/api/perguntas/{id}` | Atualiza pergunta |
| DELETE | `/api/perguntas/{id}` | Deleta pergunta |

---

## 📦 Exemplos de uso

### Criar uma pergunta
`POST /api/perguntas`
```json
{
  "enunciado": "Qual é a saída de System.out.println(\"Java\".length())?",
  "tipo": "MULTIPLA_ESCOLHA",
  "nivel": "INICIANTE",
  "categoria": "STRINGS",
  "opcoes": [
    { "texto": "4", "correta": true },
    { "texto": "5", "correta": false },
    { "texto": "6", "correta": false },
    { "texto": "Java", "correta": false }
  ]
}
```

### Responder uma pergunta
`POST /api/perguntas/1/responder`
```json
{
  "opcaoId": 1
}
```

**Resposta:**
```json
{
  "correta": true,
  "mensagem": "✅ Correto! Parabéns!",
  "opcaoCorretaId": 1,
  "textoOpcaoCorreta": "int"
}
```

---

## 🗂️ Estrutura do projeto

```
src/
└── main/java/com/javaquiz/
    ├── JavaQuizApplication.java
    ├── controller/
    │   └── PerguntaController.java
    ├── service/
    │   └── PerguntaService.java
    ├── repository/
    │   └── PerguntaRepository.java
    ├── model/
    │   ├── Pergunta.java
    │   ├── Opcao.java
    │   └── enums/
    │       ├── TipoPergunta.java
    │       └── Nivel.java
    ├── dto/
    │   ├── PerguntaDTO.java
    │   ├── OpcaoDTO.java
    │   └── RespostaDTO.java
    └── exception/
        └── GlobalExceptionHandler.java
```

## 🛠️ Tecnologias
- Java 17
- Spring Boot 3.2
- Spring Data JPA
- H2 Database (dev) / troque por MySQL/PostgreSQL em produção
- Lombok
- Bean Validation
