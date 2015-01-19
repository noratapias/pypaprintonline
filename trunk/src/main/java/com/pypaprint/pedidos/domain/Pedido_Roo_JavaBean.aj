// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.domain;

import com.pypaprint.pedidos.domain.Cliente;
import com.pypaprint.pedidos.domain.Pedido;
import com.pypaprint.pedidos.domain.Producto;
import com.pypaprint.pedidos.domain.StatusPedido;
import com.pypaprint.pedidos.domain.security.Usuario;
import java.util.Date;
import java.util.Set;

privileged aspect Pedido_Roo_JavaBean {
    
    public Cliente Pedido.getCliente() {
        return this.cliente;
    }
    
    public void Pedido.setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Set<Producto> Pedido.getProductos() {
        return this.productos;
    }
    
    public void Pedido.setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
    
    public Date Pedido.getFechaDeRecepcion() {
        return this.fechaDeRecepcion;
    }
    
    public void Pedido.setFechaDeRecepcion(Date fechaDeRecepcion) {
        this.fechaDeRecepcion = fechaDeRecepcion;
    }
    
    public Date Pedido.getFechaPropuestaDeEntrega() {
        return this.fechaPropuestaDeEntrega;
    }
    
    public void Pedido.setFechaPropuestaDeEntrega(Date fechaPropuestaDeEntrega) {
        this.fechaPropuestaDeEntrega = fechaPropuestaDeEntrega;
    }
    
    public Double Pedido.getAbono() {
        return this.abono;
    }
    
    public void Pedido.setAbono(Double abono) {
        this.abono = abono;
    }
    
    public Double Pedido.getTotalBs() {
        return this.totalBs;
    }
    
    public void Pedido.setTotalBs(Double totalBs) {
        this.totalBs = totalBs;
    }
    
    public Usuario Pedido.getRecibidoPor() {
        return this.recibidoPor;
    }
    
    public void Pedido.setRecibidoPor(Usuario recibidoPor) {
        this.recibidoPor = recibidoPor;
    }
    
    public StatusPedido Pedido.getStatus() {
        return this.status;
    }
    
    public void Pedido.setStatus(StatusPedido status) {
        this.status = status;
    }
    
    public String Pedido.getDescripcion() {
        return this.descripcion;
    }
    
    public void Pedido.setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String Pedido.getComentario() {
        return this.comentario;
    }
    
    public void Pedido.setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
