// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.domain;

import com.pypaprint.pedidos.domain.PedidoProductos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PedidoProductos_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PedidoProductos.entityManager;
    
    public static final List<String> PedidoProductos.fieldNames4OrderClauseFilter = java.util.Arrays.asList("cantidad", "pedido", "producto");
    
    public static final EntityManager PedidoProductos.entityManager() {
        EntityManager em = new PedidoProductos().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PedidoProductos.countPedidoProductoses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PedidoProductos o", Long.class).getSingleResult();
    }
    
    public static List<PedidoProductos> PedidoProductos.findAllPedidoProductoses() {
        return entityManager().createQuery("SELECT o FROM PedidoProductos o", PedidoProductos.class).getResultList();
    }
    
    public static List<PedidoProductos> PedidoProductos.findAllPedidoProductoses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM PedidoProductos o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, PedidoProductos.class).getResultList();
    }
    
    public static PedidoProductos PedidoProductos.findPedidoProductos(Long id) {
        if (id == null) return null;
        return entityManager().find(PedidoProductos.class, id);
    }
    
    public static List<PedidoProductos> PedidoProductos.findPedidoProductosEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PedidoProductos o", PedidoProductos.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<PedidoProductos> PedidoProductos.findPedidoProductosEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM PedidoProductos o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, PedidoProductos.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PedidoProductos.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PedidoProductos attached = PedidoProductos.findPedidoProductos(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PedidoProductos.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PedidoProductos.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PedidoProductos PedidoProductos.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PedidoProductos merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
