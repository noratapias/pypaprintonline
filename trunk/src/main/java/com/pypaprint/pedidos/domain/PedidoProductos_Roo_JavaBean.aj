// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.domain;

import com.pypaprint.pedidos.domain.Pedido;
import com.pypaprint.pedidos.domain.PedidoProductos;
import com.pypaprint.pedidos.domain.Producto;

privileged aspect PedidoProductos_Roo_JavaBean {
    
    public Double PedidoProductos.getCantidad() {
        return this.cantidad;
    }
    
    public void PedidoProductos.setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    
    public Pedido PedidoProductos.getPedido() {
        return this.pedido;
    }
    
    public void PedidoProductos.setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public Producto PedidoProductos.getProducto() {
        return this.producto;
    }
    
    public void PedidoProductos.setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
