package com.pypaprint.pedidos.repositorys;
import com.pypaprint.pedidos.domain.Pedido;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Pedido.class)
public interface PedidoRepository {
}
