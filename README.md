
## Documentação API REST

Para correr este projecto e necessario fazer o deploy do WAR no Jboss, Tomcat ou outro servidor
e de seguida chamar os endpoints abaixo de acordo de acordo com a porta configurada;
Pode-se testar no postman ou outro tipo de app para testar servicos.




**ROOT URL**: `random-api-application/resources/generate/numbers`


### Random Number
 
Endpoints for Random Number:
POST: http://127.0.0.1:8080/random-api-application/resources/generate/numbers/random
GET: http://127.0.0.1:8080/random-api-application/resources/generate/numbers/history
GET: http://127.0.0.1:8080/random-api-application/resources/generate/numbers/pending
PUT: http://127.0.0.1:8080/random-api-application/resources/generate/numbers/treads
PUT: http://127.0.0.1:8080/random-api-application/resources/generate/numbers/requestId/cancel

