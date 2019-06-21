package br.com.cadmus.solicitacaoServico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadmus.solicitacaoServico.domain.SolicitacaoCliente;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoCliente, Long>{

}
