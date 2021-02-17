package br.com.superacaobikes.admin.repositories;

import br.com.superacaobikes.admin.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
