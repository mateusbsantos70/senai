# Documento de Projeto — Sistema de Controle de Acesso

## 1. Visão Geral

O sistema foi desenvolvido em Java com o objetivo de controlar o acesso de funcionários a diferentes áreas de um edifício corporativo. O projeto segue uma estrutura orientada a objetos, separando responsabilidades em classes distintas.

---

## 2. Estrutura de Dados Utilizada

### 2.1 Classe `usuario`
Representa um funcionário cadastrado no sistema.

| Atributo | Tipo | Descrição |
|---|---|---|
| id | String | Identificador único do usuário |
| nome | String | Nome completo |
| senhahash | String | Senha protegida com SHA-256 |
| nivel | int | Nível de acesso (1, 2 ou 3) |

### 2.2 Classe `area`
Representa uma área física do edifício.

| Atributo | Tipo | Descrição |
|---|---|---|
| nome | String | Nome da área |
| nivelminimo | int | Nível mínimo exigido para acesso |

### 2.3 Classe `registroAcesso`
Representa uma tentativa de acesso registrada no histórico.

| Atributo | Tipo | Descrição |
|---|---|---|
| idUsario | String | ID do usuário que tentou acessar |
| nomeArea | String | Nome da área acessada |
| dataHora | String | Data e hora da tentativa |
| autorizado | boolean | true = autorizado, false = negado |

### 2.4 Classe `SistemaAcesso`
Classe central que contém as listas de dados e toda a lógica do sistema.

| Atributo | Tipo | Descrição |
|---|---|---|
| usarios | ArrayList\<usuario\> | Lista de usuários cadastrados |
| Areas | ArrayList\<area\> | Lista de áreas do edifício |
| registros | ArrayList\<registroAcesso\> | Histórico de tentativas |

---

## 3. Como as Permissões Foram Modeladas

As permissões foram modeladas por meio de uma **hierarquia numérica simples**:

| Nível | Perfil | Áreas permitidas |
|---|---|---|
| 1 | Visitante | Recepção |
| 2 | Funcionário | Recepção, Escritório |
| 3 | Administrador | Todas as áreas |

A regra de verificação é:

> **Se o nível do usuário ≥ nível mínimo da área → acesso autorizado**

Essa lógica está implementada no método `verificarAcesso` da classe `SistemaAcesso`:

```java
public boolean verificarAcesso(usuario u, area a) {
    return u.nivel >= a.nivelminimo;
}
```

Essa abordagem é simples, eficiente e facilmente extensível para novos níveis.

---

## 4. Segurança de Senhas

As senhas não são armazenadas em texto puro. O sistema utiliza o algoritmo **SHA-256** da biblioteca `java.security.MessageDigest` para gerar um hash irreversível da senha no momento do cadastro. Na autenticação, o hash da senha digitada é comparado com o hash armazenado.

---

## 5. Possíveis Melhorias Futuras

### 5.1 Persistência de dados
Atualmente os dados ficam apenas em memória e são perdidos ao encerrar o programa. Uma melhoria seria salvar os dados em arquivo (CSV, JSON) ou banco de dados (SQLite, MySQL).

### 5.2 Remoção e edição de usuários
O sistema atual permite apenas cadastrar usuários. Seria importante permitir que o administrador edite ou remova cadastros.

### 5.3 Interface gráfica
Substituir o menu de texto por uma interface gráfica usando JavaFX ou Swing, tornando o sistema mais amigável.

### 5.4 Limite de tentativas de login
Bloquear o usuário após um número definido de tentativas incorretas de senha, aumentando a segurança.

### 5.5 Autenticação mais robusta
Utilizar algoritmos mais modernos como **bcrypt** ou **Argon2** no lugar do SHA-256 para proteção de senhas ainda mais segura.

### 5.6 Log em arquivo
Salvar o histórico de acessos em um arquivo de log permanente, em vez de apenas na memória.
