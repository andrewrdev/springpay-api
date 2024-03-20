# Documentação da API - Payloads  

## Introdução

Esta é a documentação da API. Aqui você encontrará informações sobre os exemplos de payloads para solicitações.

## Exemplos de Payload

### `POST: /api/users`
Insere um novo usuário no sistema

##### Exemplo de payload

```json
{
	"fullName": "José",
	"document": "111.111.111-11",
	"email": "jose@mail.com",
	"password": "123",
        "balance": 50,
	"userType": "COMMON"
}
```

```json
{
	"fullName": "Marcos",
	"document": "222.222.222-22",
	"email": "marcos@mail.com",
	"password": "123",
        "balance": 0,
	"userType": "RETAILER"
}
```  

### `POST: /api/transactions`
Registra uma nova transação entre os usuários

##### Exemplo de payload

```json
{
	"amount": 10,
	"senderId": 1,
	"receiverId": 2
}
```

## Considerações Finais

Esta é uma documentação básica da API. Certifique-se de verificar os headers necessários, possíveis códigos de status e autenticação para cada endpoint antes de utilizá-los.
