// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.domain;

import com.pypaprint.pedidos.domain.Producto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Producto_Roo_Jpa_Entity {
    
    declare @type: Producto: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Producto.id;
    
    @Version
    @Column(name = "version")
    private Integer Producto.version;
    
    public Long Producto.getId() {
        return this.id;
    }
    
    public void Producto.setId(Long id) {
        this.id = id;
    }
    
    public Integer Producto.getVersion() {
        return this.version;
    }
    
    public void Producto.setVersion(Integer version) {
        this.version = version;
    }
    
}
