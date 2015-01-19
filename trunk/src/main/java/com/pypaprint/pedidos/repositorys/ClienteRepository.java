package com.pypaprint.pedidos.repositorys;
import com.pypaprint.pedidos.domain.Cliente;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Cliente.class)
public interface ClienteRepository {
}
