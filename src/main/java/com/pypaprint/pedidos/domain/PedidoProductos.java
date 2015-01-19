package com.pypaprint.pedidos.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "pedidos_productos", finders = { "findPedidoProductosesByPedido" })
public class PedidoProductos {

    /**
     */
    @NotNull
    private Double cantidad;

    @ManyToOne
    @JoinColumn(name = "pedidoId", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "productoId", referencedColumnName = "id")
    private Producto producto;

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        System.out.println("this.productid " + this.producto.getId());
        this.entityManager.persist(this);
    }
}
