# 🔐 Sistema de Controle de Acesso

Sistema desenvolvido em Java para controlar o acesso de funcionários a diferentes áreas de um edifício corporativo.

## 📋 Funcionalidades

- Autenticação de usuários por ID e senha
- 3 níveis de acesso: **Visitante**, **Funcionário** e **Administrador**
- Verificação de permissão antes de liberar acesso a qualquer área
- Registro de histórico com data, hora, usuário e resultado (autorizado/negado)
- Senhas protegidas com hash SHA-256
- Administrador pode cadastrar novos usuários e consultar o histórico completo

## 🏗️ Estrutura do Projeto

```
src/
├── Main.java               # Ponto de entrada e menu do sistema
├── usuario/
│   └── usuario.java        # Representa um funcionário
├── area/
│   └── area.java           # Representa uma área do edifício
├── registroAcesso/
│   └── registroAcesso.java # Representa uma tentativa de acesso
└── SistemaAcesso/
    └── SistemaAcesso.java  # Lógica central do sistema
```

## 👥 Níveis de Acesso

| Nível | Perfil | Permissões |
|---|---|---|
| 1 | Visitante | Recepção |
| 2 | Funcionário | Recepção, Escritório |
| 3 | Administrador | Todas as áreas + gerenciar usuários + ver histórico |

## 🚀 Como executar

1. Clone o repositório
2. Abra o projeto no IntelliJ IDEA ou Eclipse
3. Execute a classe `Main.java`

## 🧪 Usuários de teste

| ID | Senha | Nível |
|---|---|---|
| admin01 | 1234 | Administrador |
| func01 | 1234 | Funcionário |
| visit01 | 1234 | Visitante |

## 🔒 Segurança

As senhas são armazenadas com hash SHA-256. Nenhuma senha é salva em texto puro.

## 🛠️ Tecnologias

- Java
- `java.security.MessageDigest` — hash de senha
- `java.time.LocalDateTime` — registro de data e hora
- `java.util.ArrayList` — armazenamento em memória
