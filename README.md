# SpringPay API 🚀

[![Versão](https://img.shields.io/badge/Vers%C3%A3o-1.0.0-blue.svg)]
[![Licença](https://img.shields.io/badge/Licen%C3%A7a-GPL%20v3.0-green.svg)](LICENSE.md)
[![Build Status](https://img.shields.io/badge/Build-Ok-brightgreen.svg)]


## Introdução

Bem-vindo ao SpringPay API, uma solução em Java com Spring Boot para transferências de dinheiro entre usuários comuns e lojistas. Esta API oferece funcionalidades essenciais para um sistema financeiro simples e eficiente.

## Funcionalidades Principais 🛠️

1. **Cadastro de Usuário:**
   - Endpoint: `/api/users`
   - Permite o registro de usuários com informações como nome completo, CPF, e-mail e senha. Garante unicidade de CPF/CNPJ e e-mails no sistema.

2. **Transferência de Dinheiro:**
   - Endpoint: `/api/transactions`
   - Facilita a transferência de dinheiro entre usuários e lojistas. Valida o saldo do usuário antes da transferência e utiliza um serviço externo para autorização.

3. **Notificação das Transferências:**
   - Endpoint: `/api/notifications`
   - Exibe uma notificação para o usuário se a notificação for concluída.   

## Como Utilizar 🚦

Para utilizar o SpringPay API, basta seguir os seguintes passos:

1. Clone este repositório:
   ```bash
   git clone https://github.com/andrewrdev/springpay-api.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd springpay-api
   ```

3. Configure o ambiente no application.properties: 

4. Acesse os endpoints do sistema [Ver Endpoints](./docs/endpoints.md)

5. Acesse os exemplos de payload [Ver Payloads](./docs/payload.md)

## Contribuições e Feedback 🤝

Contribuições são bem-vindas! Se encontrar problemas, tenha ideias ou queira melhorar algo, sinta-se à vontade para abrir uma [issue](https://github.com/andrewrdev/springpay-api/issues).

## Licença 📜

Este projeto é licenciado sob a [GNU General Public License v3.0]() - veja o arquivo LICENSE.md para detalhes.