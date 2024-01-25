# API de Playlists

Esta API permite gerenciar playlists e músicas associadas. Abaixo estão os principais endpoints disponíveis.

## Tecnologias Utilizadas
- Java
- Spring Boot
- Hibernate
- H2 Database
- Spring Data JPA

# Endpoints

## Playlists

### 1. Listar Playlists

- **Método:** `GET`
- **URL:** `/playlist/lists`
- **Descrição:** Retorna todas as playlists cadastradas.

### 2. Detalhes de uma Playlist

- **Método:** `GET`
- **URL:** `/playlist/lists/{nome}`
- **Descrição:** Retorna detalhes de uma playlist específica pelo nome.

### 3. Cadastrar nova playlist

- **Método:** `POST`
- **URL:** `/playlist/lists`
- **Descrição:** Adiciona uma nova playlist. Envie um JSON no corpo da requisição.

```json
{
    "nome": "nome-playlist",
    "descricao": "descricao"
}
```

### 4. Remover uma playlist

- **Método:** `DELETE`
- **URL:** `/playlist/lists/{nome}`
- **Descrição:** Remove uma playlist.

## Músicas

### 1. Listar Músicas

- **Método:** `GET`
- **URL:** `/musicas/lists`
- **Descrição:** Retorna todas as músicas cadastradas.

### 2. Detalhes de uma música

- **Método:** `GET`
- **URL:** `/musicas/lists/{nome}`
- **Descrição:** Retorna detalhes de uma música específica pelo nome.

### 3. Cadastrar nova música

- **Método:** `POST`
- **URL:** `/musicas/lists`
- **Descrição:** Adiciona uma nova playlist. Envie um JSON no corpo da requisição.

```json
{
    "titulo": "titulo-musica",
    "artista": "artista",
    "ano": "2010",
    "genero": "genero",
    "playlist": {
        "nome": "Nome-playlist"
    },
    "album": "exemplo"
}
```

### 4. Remover uma música

- **Método:** `DELETE`
- **URL:** `/musicas/lists/{nome}`
- **Descrição:** Remove uma playlist.

## Banco de Dados
A aplicação utiliza o H2 Database. Os dados são armazenados em memória durante a execução.

### Configuração do H2 Console
- URL: http://localhost:8080/h2
- Credenciais: admin / 1234

# Executar projeto
1. Clone o repositório
2. Execute o arquivo: ApirestApplication.java

# Acessar API
Após a execução bem-sucedida, a API estará disponível em http://localhost:8080. Utilize os endpoints mencionados acima para interagir com a API.
