package br.com.superacaobikes.admin.repositories;

import br.com.superacaobikes.admin.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
