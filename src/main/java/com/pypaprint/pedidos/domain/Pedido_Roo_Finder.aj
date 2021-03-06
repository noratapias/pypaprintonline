// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.domain;

import com.pypaprint.pedidos.domain.Cliente;
import com.pypaprint.pedidos.domain.Pedido;
import com.pypaprint.pedidos.domain.StatusPedido;
import com.pypaprint.pedidos.domain.security.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Pedido_Roo_Finder {
    
    public static Long Pedido.countFindPedidoesByCliente(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("The cliente argument is required");
        EntityManager em = Pedido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Pedido AS o WHERE o.cliente = :cliente", Long.class);
        q.setParameter("cliente", cliente);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Pedido.countFindPedidoesByStatus(StatusPedido status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Pedido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Pedido AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<Pedido> Pedido.findPedidoesByCliente(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("The cliente argument is required");
        EntityManager em = Pedido.entityManager();
        TypedQuery<Pedido> q = em.createQuery("SELECT o FROM Pedido AS o WHERE o.cliente = :cliente", Pedido.class);
        q.setParameter("cliente", cliente);
        return q;
    }
    
    public static TypedQuery<Pedido> Pedido.findPedidoesByCliente(Cliente cliente, String sortFieldName, String sortOrder) {
        if (cliente == null) throw new IllegalArgumentException("The cliente argument is required");
        EntityManager em = Pedido.entityManager();
        String jpaQuery = "SELECT o FROM Pedido AS o WHERE o.cliente = :cliente";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Pedido> q = em.createQuery(jpaQuery, Pedido.class);
        q.setParameter("cliente", cliente);
        return q;
    }
    
    public static TypedQuery<Pedido> Pedido.findPedidoesByRecibidoPor(Usuario recibidoPor, String sortFieldName, String sortOrder) {
        if (recibidoPor == null) throw new IllegalArgumentException("The recibidoPor argument is required");
        EntityManager em = Pedido.entityManager();
        String jpaQuery = "SELECT o FROM Pedido AS o WHERE o.recibidoPor = :recibidoPor";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Pedido> q = em.createQuery(jpaQuery, Pedido.class);
        q.setParameter("recibidoPor", recibidoPor);
        return q;
    }
    
    public static TypedQuery<Pedido> Pedido.findPedidoesByStatus(StatusPedido status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Pedido.entityManager();
        TypedQuery<Pedido> q = em.createQuery("SELECT o FROM Pedido AS o WHERE o.status = :status", Pedido.class);
        q.setParameter("status", status);
        return q;
    }
    
    public static TypedQuery<Pedido> Pedido.findPedidoesByStatus(StatusPedido status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Pedido.entityManager();
        String jpaQuery = "SELECT o FROM Pedido AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Pedido> q = em.createQuery(jpaQuery, Pedido.class);
        q.setParameter("status", status);
        return q;
    }
    
}
