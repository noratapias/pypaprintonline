// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.domain;

import com.pypaprint.pedidos.domain.StatusPedido;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect StatusPedido_Roo_Jpa_Entity {
    
    declare @type: StatusPedido: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long StatusPedido.id;
    
    @Version
    @Column(name = "version")
    private Integer StatusPedido.version;
    
    public Long StatusPedido.getId() {
        return this.id;
    }
    
    public void StatusPedido.setId(Long id) {
        this.id = id;
    }
    
    public Integer StatusPedido.getVersion() {
        return this.version;
    }
    
    public void StatusPedido.setVersion(Integer version) {
        this.version = version;
    }
    
}
