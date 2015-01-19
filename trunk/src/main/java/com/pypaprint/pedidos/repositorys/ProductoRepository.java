package com.pypaprint.pedidos.repositorys;
import com.pypaprint.pedidos.domain.Producto;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Producto.class)
public interface ProductoRepository {
}
