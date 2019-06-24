package br.com.cadmus.solicitacaoServico.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadmus.solicitacaoServico.Service.ClienteService;
import br.com.cadmus.solicitacaoServico.domain.Cliente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Cliente", tags = { "Cliente" })
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation(value = "${swagger.api.operation.cliente.criar.value}", notes = "${swagger.api.operation.cliente.criar.notes}", tags = {
	"Cliente" })
	@PostMapping("novo/{nome}")
	public String criar(@PathVariable(value = "nome")String nome) {
		
		if(nome.length()>3) {
			try {
				
				String urlDecoder = URLDecoder.decode(nome, "UTF-8");
				Cliente cli = clienteService.criar(urlDecoder);
				if(cli != null)
					return "O cliente" + cli.toString() + " foi criado com sucesso.";
			} catch (UnsupportedEncodingException e) {
				return "O cliente não foi criado";
			}
		}
		return "O cliente não foi criado";
		
		
	}
	
	@ApiOperation(value = "${swagger.api.operation.cliente.buscarId.value}", notes = "${swagger.api.operation.cliente.buscarID.notes}", tags = {
	"Cliente" })
	@GetMapping("/{id}")
	public Cliente buscar(@PathVariable(value="id") Long id) {
		Optional<Cliente> cli = clienteService.encontrarCliente(id);
		
		if(cli.isPresent()) {
			Cliente response = cli.get();
			return response;
		}
		return null;
	}
}
