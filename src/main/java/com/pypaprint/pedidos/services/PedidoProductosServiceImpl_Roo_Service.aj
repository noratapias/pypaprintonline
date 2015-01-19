// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.services;

import com.pypaprint.pedidos.domain.PedidoProductos;
import com.pypaprint.pedidos.services.PedidoProductosServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PedidoProductosServiceImpl_Roo_Service {
    
    declare @type: PedidoProductosServiceImpl: @Service;
    
    declare @type: PedidoProductosServiceImpl: @Transactional;
    
    public long PedidoProductosServiceImpl.countAllPedidoProductoses() {
        return PedidoProductos.countPedidoProductoses();
    }
    
    public void PedidoProductosServiceImpl.deletePedidoProductos(PedidoProductos pedidoProductos) {
        pedidoProductos.remove();
    }
    
    public PedidoProductos PedidoProductosServiceImpl.findPedidoProductos(Long id) {
        return PedidoProductos.findPedidoProductos(id);
    }
    
    public List<PedidoProductos> PedidoProductosServiceImpl.findAllPedidoProductoses() {
        return PedidoProductos.findAllPedidoProductoses();
    }
    
    public List<PedidoProductos> PedidoProductosServiceImpl.findPedidoProductosEntries(int firstResult, int maxResults) {
        return PedidoProductos.findPedidoProductosEntries(firstResult, maxResults);
    }
    
    public void PedidoProductosServiceImpl.savePedidoProductos(PedidoProductos pedidoProductos) {
        pedidoProductos.persist();
    }
    
    public PedidoProductos PedidoProductosServiceImpl.updatePedidoProductos(PedidoProductos pedidoProductos) {
        return pedidoProductos.merge();
    }
    
}
