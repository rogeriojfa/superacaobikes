package br.com.superacaobikes.admin.repositories;

import br.com.superacaobikes.admin.domain.PlanoContas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoContasRepository extends JpaRepository<PlanoContas, Integer> {

}
