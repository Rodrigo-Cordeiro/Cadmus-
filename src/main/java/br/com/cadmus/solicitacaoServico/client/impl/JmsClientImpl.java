package br.com.cadmus.solicitacaoServico.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadmus.solicitacaoServico.Service.ClienteService;
import br.com.cadmus.solicitacaoServico.client.JmsClient;
import br.com.cadmus.solicitacaoServico.consumer.JmsConsumer;
import br.com.cadmus.solicitacaoServico.domain.Cliente;
import br.com.cadmus.solicitacaoServico.producer.JmsProducer;

@Service
public class JmsClientImpl implements JmsClient{
 
  @Autowired
  JmsConsumer jmsConsumer;
  
  @Autowired
  JmsProducer jmsProducer;
  
  @Autowired
  ClienteService clienteService;
  
  @Override
  public void send(String msg) {
    jmsProducer.send(msg);
  }
 
  
  @Override
  public String receive(Long idCliente) {
	
	 if(clienteService.encontrarCliente(idCliente)!= null) {
		Cliente response = clienteService.adicionaSolicitacao(jmsConsumer.receive(), idCliente);
		if(response != null)
			return response.getPedido().toString();
	 }
	  
    return "Cliente não encontrado" ;
  }
 
}