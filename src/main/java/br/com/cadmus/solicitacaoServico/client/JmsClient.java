package br.com.cadmus.solicitacaoServico.client;

public interface JmsClient {
	public void send(String msg);

	public String receive(Long idCliente);
}
