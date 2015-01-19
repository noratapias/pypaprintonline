// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.services;

import com.pypaprint.pedidos.domain.security.Usuario;
import com.pypaprint.pedidos.services.UsuarioServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UsuarioServiceImpl_Roo_Service {
    
    declare @type: UsuarioServiceImpl: @Service;
    
    declare @type: UsuarioServiceImpl: @Transactional;
    
    public long UsuarioServiceImpl.countAllUsuarios() {
        return Usuario.countUsuarios();
    }
    
    public void UsuarioServiceImpl.deleteUsuario(Usuario usuario) {
        usuario.remove();
    }
    
    public Usuario UsuarioServiceImpl.findUsuario(Long id) {
        return Usuario.findUsuario(id);
    }
    
    public List<Usuario> UsuarioServiceImpl.findAllUsuarios() {
        return Usuario.findAllUsuarios();
    }
    
    public List<Usuario> UsuarioServiceImpl.findUsuarioEntries(int firstResult, int maxResults) {
        return Usuario.findUsuarioEntries(firstResult, maxResults);
    }
    
    public void UsuarioServiceImpl.saveUsuario(Usuario usuario) {
        usuario.persist();
    }
    
    public Usuario UsuarioServiceImpl.updateUsuario(Usuario usuario) {
        return usuario.merge();
    }
    
}
