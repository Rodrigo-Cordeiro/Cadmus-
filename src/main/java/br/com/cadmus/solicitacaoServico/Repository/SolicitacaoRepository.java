package br.com.cadmus.solicitacaoServico.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.cadmus.solicitacaoServico.domain.SolicitacaoCliente;

@Repository
public interface SolicitacaoRepository extends PagingAndSortingRepository<SolicitacaoCliente, Long>{

}
