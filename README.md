# Cadmus-
Projeto de Solicitação Serviços

- Para rodar o projeto é necessário rodar o ActiveMQ a partir da imagem do docker:
    *docker run -p 61616:61616 -p 8161:8161 -t webcenter/activemq

- Após a inicialização do Docker e da Java, deverá ser acessado o endereço:
     *http://localhost:8080/swagger-ui.html#/
     
# SOBRE A APLICAÇÃO CRIADA:
  Objetivo: Fazer solicitações de serviços em geral por envio de mensagem. 

- Passos a serem seguidos:
  1) Cadastrar Cliente 
  2) Realizar a chamada do Produce passando o id do cliente cadastrado e a mensagem desejada. 
  3) Realizar a chamada do receive (A partir desse ponto será persistido no banco de dados as informações passadas, de modo que a entidade cliente seja atualizada)
  4) Realizar um GET pelo ID - para verificar se cliente agora possui um pedido associacao
  5) Realizar um GET pela lista pra verificar se a mesma está populada. (ENDPOINT PAGINADO) 
  
  
