# Sub II - CC Vendas API
Projeto Sub 2 FIAP: API Veiculos - Este projeto é uma API desenvolvida para o gerenciamento e vendas veiculos. O projeto foi construído seguindo os princípios do SOLID, Arquitetura Hexagonal e Clean Architecture de forma prescritiva.

## Tecnologias Utilizadas

| Tecnologia                                                                                                                                                                     |                                                                                                                                                       |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/java.png" width="40" alt="Logo Java"> <br> Java 21                       | Java é uma linguagem de programação, orientada a objetos e multiplataforma, com a versão 21 que disponibiliza diversos recursos modernos.             |
| <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/spring_boot.png" width="40" alt="Logo Spring Boot"> <br> Spring Boot 4.x | Framework Java de código aberto, baseado no Spring Framework,  com o objetivo de simplificar o desenvolvimento de aplicações web e microsserviços.    |
| H2 Database                                                                                                                                                                    | Sistema de gerenciamento de banco de dados relacional (SGBDR) de código aberto, leve e eficiente, desenvolvido em Java.                               |
| <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/swagger.png" width="40" alt="Logo Swagger"> <br> Spring Doc / Swagger    | Biblioteca Java que automatiza a criação de documentação de API para projetos Spring Boot.                                                           |
| <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/docker.png" width="40" alt="Logo Docker"> <br> Docker                    | Plataforma de código aberto (open-source) que automatiza a criação, implementação e administração de aplicações em contêineres.                       |
| <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/kubernetes.png" width="40" alt="Logo Kubernetes"> <br> Kubernetes            | Plataforma de código aberto (open-source) para automatizar a implantação, escalonamento e administração de aplicações em contêineres, como o Docker.  |


## Arquitetura do Projeto

O projeto utiliza a estrutura de Ports and Adapters (Arquitetura Hexagonal). O objetivo é isolar o "Coração" da aplicação (Domínio) de influências externas

### Divisão de Camadas:

*Dominio*: Contém a lógica de negócio.
- model: Entidades de negócio (ex: Venda, Produto).
    
- repository: Interfaces que definem o que o domínio precisa (Ports).


*Aplicaçao*: Media a comunicação.
- usecase: Implementação das regras de negócio.

- dto: Objetos de transferência de dados para evitar exposição do modelo.

*Infraestrutura*: Detalha da implementação.

- web: Controladores REST (Adapters de entrada).

- persistence: Implementação JpaRepository e Entidades de Banco (Adapters de saída).

- config: Configurações de Beans e Segurança.

```
sub_II-cc-vendas/
├── src/main/java/com/cc/vendas/
│   ├── aplicacao/        <-- Casos de uso e Orquestração
│   ├── dominio/          <-- Regras de negocio e interfaces (Ports)
│   ├── infraestrutura/   <-- Implementações técnicas
│   └── shared/           <-- Código comum a todas as camadas
├── k8s/                  <-- Arquivos de orquestração
├── Dockerfile            <-- Construção da imagem do container
└── docker-compose.yml    <-- Orquestração local
```

##  Formas de Execução

| Pré-requisitos                      |
|-------------------------------------|
| Java JDK 21                         |
| Maven 3.9+                          |
| Docker Desktop                      |
| Cluster Kubernetes Ativo            |
| Kubectl CLI                         |
| 4GB Memória RAM dedicados ao Docker |
| Docker Compose V2                   |

*1ª Opção:* Kubernetes

Para rodar utilize os comandos abaixo:

```
docker build -t subiifiap-core:latest .
# Irá realizar o build da aplicação

kubectl apply -f k8s/
# Irá aplicar s arquivos de manifesto do kubernetes

kubectl get svc subiifiap-service
kubectl get endpoints subiifiap-service
# formas de confirmar a execução do serviço
```

*2ª Opção:* Docker Compose

```
docker compose up
```

## Documentação e Endpoints

| Pré-requisitos |                                             |
|----------------|---------------------------------------------|
| Swagger UI     | http://localhost:8080/swagger-ui/index.html |
| OpenAPI        | http://localhost:8080/v3/api-docs           |
| Health Check   | http://localhost:8080/actuator/health       |

### _*/veiculos*_

    GET /api/veiculos/disponiveis
    Retorna a lista de veículos disponíveis para venda.

    Observação: Pode retornar lista vazia.
    
    Possíveis respostas:
    
    200 – Lista retornada com sucesso
    500 – Erro interno

___
    GET /api/veiculos/vendidos
    Retorna todos os veículos que já foram vendidos.
    
    Observação: Pode retornar lista vazia.
    
    Possíveis respostas:
    
    200 – Lista retornada com sucesso
    500 – Erro interno
___
    GET /api/veiculos/{idVeiculo}
    Recupera os dados de um veículo específico a partir do seu identificador UUID.
    
    Possíveis respostas:
    
    200 – Veículo encontrado
    400 – UUID inválido
    404 – Veículo não localizado
    500 – Erro interno
___
    POST /api/veiculos
    Realiza o cadastro de um novo veículo na base.
    
    Possíveis respostas:
    
    201 – Veículo cadastrado com sucesso
    400 – Dados inválidos
    404 – Referência não encontrada (caso aplicável)
___
    PUT /api/veiculos
    Possíveis respostas:

    200 – Atualização realizada com sucesso
    400 – Dados inválidos
    404 – Veículo não encontrado
    422 – Violação de regra de negócio
    500 – Erro interno
___
### _*/vendas*_
    POST /api/vendas/{idVeiculo}/registrar-venda
    Registra a venda de um veículo disponível.
    
    Fluxo esperado:
    
    Valida se o veículo existe (cadastrado anteriormente no POST /api/veiculos)
    
    Verifica se está disponível
    
    Marca como vendido
    
    Persiste a operação
    
    Possíveis respostas:
    
    204 – Venda registrada com sucesso
    400 – Dados inválidos
    404 – Veículo não encontrado
    422 – Regra de negócio violada
___

### _*/webhook*_
    POST /pagamentos
    Endpoint para receber eventos de pagamento externos (webhook).

    Objetivo:
    Atualizar o status de uma venda/pagamento com base na notificação enviada por um provedor externo.
    
    Possível resposta:
    
    200 – Evento recebido e processado


## Complementar:

O banco de dados *H2 Database* está em execução junto a aplicação para demonstração, cada novo deploy ele será apagado e os dados devem ser adicionados novamente. 

## Exemlos de requisições
 
*POST /api/veiculos* 
```
{
    "marca": "Ford",
    "modelo": "Mustang Fastback",
    "cor": "Vermelho Wimbledon",
    "ano": 1965,
    "preco": 185000.00
},
{
    "marca": "Ford",
    "modelo": "Mustang Shelby GT500",
    "cor": "Azul Nightmist",
    "ano": 1967,
    "preco": 420000.00
},
{
    "marca": "Ford",
    "modelo": "Mustang Boss 429",
    "cor": "Preto Raven",
    "ano": 1969,
    "preco": 780000.00
},
{
    "marca": "Ford",
    "modelo": "Mustang Mach 1",
    "cor": "Amarelo Grabber",
    "ano": 1970,
    "preco": 265000.00
}

```
