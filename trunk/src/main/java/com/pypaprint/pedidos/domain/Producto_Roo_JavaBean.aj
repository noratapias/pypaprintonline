// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.domain;

import com.pypaprint.pedidos.domain.Producto;

privileged aspect Producto_Roo_JavaBean {
    
    public String Producto.getNombre() {
        return this.nombre;
    }
    
    public void Producto.setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String Producto.getDescripcion() {
        return this.descripcion;
    }
    
    public void Producto.setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Double Producto.getPrecio() {
        return this.precio;
    }
    
    public void Producto.setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public Long Producto.getCantidad() {
        return this.cantidad;
    }
    
    public void Producto.setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    
    public Double Producto.getDuracionEnProduccion() {
        return this.duracionEnProduccion;
    }
    
    public void Producto.setDuracionEnProduccion(Double duracionEnProduccion) {
        this.duracionEnProduccion = duracionEnProduccion;
    }
    
    public String Producto.getCodigo() {
        return this.codigo;
    }
    
    public void Producto.setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
