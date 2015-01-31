// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.domain;

import com.pypaprint.pedidos.domain.StatusPedido;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect StatusPedido_Roo_Finder {
    
    public static Long StatusPedido.countFindStatusPedidoesByStatusEquals(String status) {
        if (status == null || status.length() == 0) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = StatusPedido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM StatusPedido AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }
    
    public static Long StatusPedido.countFindStatusPedidoesByStatus_propertieEquals(String status_propertie) {
        if (status_propertie == null || status_propertie.length() == 0) throw new IllegalArgumentException("The status_propertie argument is required");
        EntityManager em = StatusPedido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM StatusPedido AS o WHERE o.status_propertie = :status_propertie", Long.class);
        q.setParameter("status_propertie", status_propertie);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<StatusPedido> StatusPedido.findStatusPedidoesByStatusEquals(String status) {
        if (status == null || status.length() == 0) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = StatusPedido.entityManager();
        TypedQuery<StatusPedido> q = em.createQuery("SELECT o FROM StatusPedido AS o WHERE o.status = :status", StatusPedido.class);
        q.setParameter("status", status);
        return q;
    }
    
    public static TypedQuery<StatusPedido> StatusPedido.findStatusPedidoesByStatusEquals(String status, String sortFieldName, String sortOrder) {
        if (status == null || status.length() == 0) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = StatusPedido.entityManager();
        String jpaQuery = "SELECT o FROM StatusPedido AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<StatusPedido> q = em.createQuery(jpaQuery, StatusPedido.class);
        q.setParameter("status", status);
        return q;
    }
    
    public static TypedQuery<StatusPedido> StatusPedido.findStatusPedidoesByStatus_propertieEquals(String status_propertie) {
        if (status_propertie == null || status_propertie.length() == 0) throw new IllegalArgumentException("The status_propertie argument is required");
        EntityManager em = StatusPedido.entityManager();
        TypedQuery<StatusPedido> q = em.createQuery("SELECT o FROM StatusPedido AS o WHERE o.status_propertie = :status_propertie", StatusPedido.class);
        q.setParameter("status_propertie", status_propertie);
        return q;
    }
    
    public static TypedQuery<StatusPedido> StatusPedido.findStatusPedidoesByStatus_propertieEquals(String status_propertie, String sortFieldName, String sortOrder) {
        if (status_propertie == null || status_propertie.length() == 0) throw new IllegalArgumentException("The status_propertie argument is required");
        EntityManager em = StatusPedido.entityManager();
        String jpaQuery = "SELECT o FROM StatusPedido AS o WHERE o.status_propertie = :status_propertie";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<StatusPedido> q = em.createQuery(jpaQuery, StatusPedido.class);
        q.setParameter("status_propertie", status_propertie);
        return q;
    }
    
}