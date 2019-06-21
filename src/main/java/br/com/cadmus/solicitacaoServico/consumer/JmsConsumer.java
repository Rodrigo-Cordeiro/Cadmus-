package br.com.cadmus.solicitacaoServico.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.cadmus.solicitacaoServico.Service.ClienteService;
import br.com.cadmus.solicitacaoServico.Service.SolicitacaoClienteService;
import br.com.cadmus.solicitacaoServico.domain.SolicitacaoCliente;

@Component
public class JmsConsumer {
  @Autowired
  JmsTemplate jmsTemplate;
  
  @Autowired
  SolicitacaoClienteService solicitacaoService;
  
  @Autowired
  ClienteService clienteService;
  
  @Value("${jms.queue.destination}")
  String destinationQueue;
  
  public SolicitacaoCliente receive(){
	 SolicitacaoCliente solicitacao = solicitacaoService.criar((String)jmsTemplate.receiveAndConvert(destinationQueue));
    return solicitacao; 
  }
}