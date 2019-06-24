package br.com.cadmus.solicitacaoServico.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadmus.solicitacaoServico.Service.ClienteService;
import br.com.cadmus.solicitacaoServico.Service.SolicitacaoClienteService;
import br.com.cadmus.solicitacaoServico.client.JmsClient;
import br.com.cadmus.solicitacaoServico.domain.Cliente;
import br.com.cadmus.solicitacaoServico.domain.SolicitacaoCliente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "WebController", tags = { "WebController" })
@RestController
public class WebController {

	@Autowired
	private JmsClient jsmClient;

	@Autowired
	private ClienteService clienteService;

	private Long idContexto;
	
	@Autowired
	SolicitacaoClienteService solicitacaoService;

	@ApiOperation(value = "${swagger.api.operation.webController.enviar.value}", notes = "${swagger.api.operation.webController.enviar.notes}", tags = {
	"WebController" })
	@PostMapping(value = "/produce")
	public String produce(@RequestParam(value = "idCliente") Long id, @RequestParam(value = "msg") String mensagem) {

		Optional<Cliente> cliente = clienteService.encontrarCliente(id);

		if (cliente.isPresent()) {
			Cliente response = cliente.get();
			idContexto = id;
			jsmClient.send(mensagem + " - Detalhes do Cliente: " + response.toString());
			
			return "Sucesso: Mensagem enviada";
		}
		return "Erro: Mensagem não enviada !";

	}

	@ApiOperation(value = "${swagger.api.operation.webController.receber.value}", notes = "${swagger.api.operation.webController.receber.notes}", tags = {
	"WebController" })
	@GetMapping(value = "/receive")
	public String receive() {
		return jsmClient.receive(idContexto);
	}
	
	@ApiOperation(value = "${swagger.api.operation.webController.listarSolicitacoes.value}", notes = "${swagger.api.operation.webController.listarSolicitacoes.notes}", tags = {
	"WebController" })
	@GetMapping(value="/solicitacoes")
	public Page<SolicitacaoCliente> listarSolicitacoes(Pageable pageable) {
		
		return solicitacaoService.listarSolicitacoes(pageable);
	}
	

}