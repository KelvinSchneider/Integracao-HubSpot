[JAVA]: https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white
[SPRINGBOOT]: https://img.shields.io/badge/spring%20boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white

<h1 align="center" style="font-weight: bold;">Case Técnico: Integração com HubSpot</h1>

![Java][JAVA]
![Spring Boot][SPRINGBOOT]

<p align="center">
 <a href="#about">Sobre</a> • 
 <a href="#started">Primeiros passos</a> • 
 <a href="#routes">Endpoints da aplicação</a> • 
 <a href="#routes">Testando o Projeto</a> •
  <a href="#decisions">Decisões Técnicas</a> •
  <a href="#future">Futuras Melhorias</a> •
</p>

<h2 id="about">📌 Sobre</h2>
API REST em Java para integrar com a API do HubSpot, implementando autenticação via OAuth 2.0, mais especificamente com o fluxo de authorization code flow, a implementação de endpoint de integração com a API e o recebimento de notificações via webhooks.

<h2 id="started">🚀 Primeiros passos</h2>
<h3>Pré-requisitos</h3>
- Java 17 <br>
- Maven <br>
- Conta no HubSpot <br>
- Ngrok (opcional): Para testar webhooks localmente <br>

<h3>Clonagem e execução</h3>

```bash
git clone https://github.com/KelvinSchneider/Integracao-HubSpot.git
cd Integracao-HubSpo
mvn springboot:run
```

<h2 id="routes">📍 Endpoints da aplicação</h2>

| rota                 | método | descrição                                          
|----------------------|--------|--------------------------------------------
| <kbd>/auth/authorize-url</kbd>     | GET | Retorna a URL de autorização para iniciar o fluxo OAuth com o HubSpot. 
| <kbd>/auth/callback?code=</kbd>     | GET | Recebe o código de autorização fornecido pelo HubSpot e realiza a troca pelo token de acesso.
| <kbd>/contacts</kbd>               | POST | Cria um novo contato.
| <kbd>/webhook/contact-creation</kbd>                   | POST | Recebe Webhook para Criação de Contatos.

<h2 id="test">💻 Testando o Projeto</h2>

1. Gere a URL de Autorização: <br>
  - Acesse GET /auth/authorize-url para obter a URL de autorização. <br>
  - Através da URL, Faça login no HubSpot e autorize o aplicativo. <br>
  - O HubSpot irá chamar a api de /callback e retornar o token, que será salvo em cache.

2. Crie um Contato: <br>
  - Envie uma requisição POST /contacts com os dados do contato. Exemplo:
```bash
{
  "email": "teste@gmail.com",
  "firstName": "Teste",
  "lastName": "Da Silva",
  "phone": "(123) 456-7890"
}
```

3. Teste o Webhook:
  - O HubSpot ira identificar o evento acionado 'contact.creation' e chamar o endpoint /webhook/contact-creation da aplicação.

<h2 id="decisions">Decisões Técnicas</h2>

Java 17 e Spring framework: Escolhido por sua facilidade de configuração e familiaridade com a tecnologia. <br>
NGROK: Escolhido para facilitar a comunicação do webhook com a api. <br>

<h2 id="future">Futuras Melhorias</h2>
- Integrar ferramentas de monitoramento. <br>
- Implementar testes unitários para segurança do código. <br>
- Adicionar novos cadastros e busca de dados do CRM da HubSpot.


