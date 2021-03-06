// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.domain.security;

import com.pypaprint.pedidos.domain.security.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Usuario_Roo_Finder {
    
    public static Long Usuario.countFindUsuariosByUsernameEquals(String username) {
        if (username == null || username.length() == 0) throw new IllegalArgumentException("The username argument is required");
        EntityManager em = Usuario.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Usuario AS o WHERE o.username = :username", Long.class);
        q.setParameter("username", username);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<Usuario> Usuario.findUsuariosByUsernameEquals(String username) {
        if (username == null || username.length() == 0) throw new IllegalArgumentException("The username argument is required");
        EntityManager em = Usuario.entityManager();
        TypedQuery<Usuario> q = em.createQuery("SELECT o FROM Usuario AS o WHERE o.username = :username", Usuario.class);
        q.setParameter("username", username);
        return q;
    }
    
    public static TypedQuery<Usuario> Usuario.findUsuariosByUsernameEquals(String username, String sortFieldName, String sortOrder) {
        if (username == null || username.length() == 0) throw new IllegalArgumentException("The username argument is required");
        EntityManager em = Usuario.entityManager();
        String jpaQuery = "SELECT o FROM Usuario AS o WHERE o.username = :username";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Usuario> q = em.createQuery(jpaQuery, Usuario.class);
        q.setParameter("username", username);
        return q;
    }
    
}
