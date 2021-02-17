package br.com.superacaobikes.admin.repositories;

import br.com.superacaobikes.admin.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
