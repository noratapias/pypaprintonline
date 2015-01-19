// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.services;

import com.pypaprint.pedidos.domain.Producto;
import com.pypaprint.pedidos.repositorys.ProductoRepository;
import com.pypaprint.pedidos.services.ProductoServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ProductoServiceImpl_Roo_Service {
    
    declare @type: ProductoServiceImpl: @Service;
    
    declare @type: ProductoServiceImpl: @Transactional;
    
    @Autowired
    ProductoRepository ProductoServiceImpl.productoRepository;
    
    public long ProductoServiceImpl.countAllProductoes() {
        return productoRepository.count();
    }
    
    public void ProductoServiceImpl.deleteProducto(Producto producto) {
        productoRepository.delete(producto);
    }
    
    public Producto ProductoServiceImpl.findProducto(Long id) {
        return productoRepository.findOne(id);
    }
    
    public List<Producto> ProductoServiceImpl.findAllProductoes() {
        return productoRepository.findAll();
    }
    
    public List<Producto> ProductoServiceImpl.findProductoEntries(int firstResult, int maxResults) {
        return productoRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void ProductoServiceImpl.saveProducto(Producto producto) {
        productoRepository.save(producto);
    }
    
    public Producto ProductoServiceImpl.updateProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    
}