package com.pypaprint.pedidos.domain.security;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJson(deepSerialize = true)
@RooJpaActiveRecord(finders = { "findAuthorityPrincipalAssignmentsByUsername" })
public class AuthorityPrincipalAssignment {

    /**
     */
    @ManyToOne
    private Usuario username;

    /**
     */
    @ManyToOne
    private Authority permiso;
}
