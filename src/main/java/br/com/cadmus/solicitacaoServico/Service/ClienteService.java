package br.com.cadmus.solicitacaoServico.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadmus.solicitacaoServico.Repository.ClienteRepository;
import br.com.cadmus.solicitacaoServico.domain.Cliente;
import br.com.cadmus.solicitacaoServico.domain.SolicitacaoCliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;
	
	public Optional<Cliente> encontrarCliente(Long id) {
		return clienteRepo.findById(id);
	}
	
	public Cliente criar(String nmCliente) {
		Cliente cliente = new Cliente();
		cliente.setNmCliente(nmCliente);
		
		return clienteRepo.save(cliente);
	}
	
	public Cliente adicionaSolicitacao(SolicitacaoCliente solicitacao, Long idCliente) {
		Optional<Cliente> cliente = encontrarCliente(idCliente);
		
		if(cliente.isPresent()) {
			Cliente response = cliente.get();
			response.setPedido(solicitacao);
			return clienteRepo.save(response);
		}
		return null;
	}
	
	public List<Cliente> encontrarTodos() {
		return clienteRepo.findAll();
	}
}
