// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.services;

import com.pypaprint.pedidos.domain.Cliente;
import com.pypaprint.pedidos.services.ClienteService;
import java.util.List;

privileged aspect ClienteService_Roo_Service {
    
    public abstract long ClienteService.countAllClientes();    
    public abstract void ClienteService.deleteCliente(Cliente cliente);    
    public abstract Cliente ClienteService.findCliente(Long id);    
    public abstract List<Cliente> ClienteService.findAllClientes();    
    public abstract List<Cliente> ClienteService.findClienteEntries(int firstResult, int maxResults);    
    public abstract void ClienteService.saveCliente(Cliente cliente);    
    public abstract Cliente ClienteService.updateCliente(Cliente cliente);    
}