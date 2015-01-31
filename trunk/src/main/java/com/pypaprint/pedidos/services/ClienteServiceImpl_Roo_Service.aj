// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.services;

import com.pypaprint.pedidos.domain.Cliente;
import com.pypaprint.pedidos.repositorys.ClienteRepository;
import com.pypaprint.pedidos.services.ClienteServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ClienteServiceImpl_Roo_Service {
    
    declare @type: ClienteServiceImpl: @Service;
    
    declare @type: ClienteServiceImpl: @Transactional;
    
    @Autowired
    ClienteRepository ClienteServiceImpl.clienteRepository;
    
    public long ClienteServiceImpl.countAllClientes() {
        return clienteRepository.count();
    }
    
    public void ClienteServiceImpl.deleteCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
    
    public Cliente ClienteServiceImpl.findCliente(Long id) {
        return clienteRepository.findOne(id);
    }
    
    public List<Cliente> ClienteServiceImpl.findAllClientes() {
        return clienteRepository.findAll();
    }
    
    public List<Cliente> ClienteServiceImpl.findClienteEntries(int firstResult, int maxResults) {
        return clienteRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void ClienteServiceImpl.saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    
    public Cliente ClienteServiceImpl.updateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
}