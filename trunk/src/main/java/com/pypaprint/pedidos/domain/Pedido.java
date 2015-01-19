package com.pypaprint.pedidos.domain;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.mail.internet.MimeMessage;
import javax.persistence.ManyToOne;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;

import com.pypaprint.pedidos.domain.security.Usuario;

import org.springframework.roo.addon.json.RooJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

@RooJavaBean
@RooToString
@RooJson(deepSerialize = true)
@RooJpaActiveRecord(finders = { "findPedidoesByRecibidoPor", "findPedidoesByCliente", "findPedidoesByStatus" })
public class Pedido {

    /**
     */
    @ManyToOne
    private Cliente cliente;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Producto> productos = new HashSet<Producto>();

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaDeRecepcion;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaPropuestaDeEntrega;

    /**
     */
    private Double abono;

    /**
     */
    private Double totalBs;

    /**
     */
    @ManyToOne
    private Usuario recibidoPor;

    /**
     */
    @ManyToOne
    private StatusPedido status;
    
    @Size(max = 150)
    private String descripcion;
    
    @Column(nullable=true)
    @Size(max = 150)
    private String comentario;

    public static Long countFindPedidoesByRecibidoPor(Usuario recibidoPor) {
        if (recibidoPor == null) throw new IllegalArgumentException("The recibidoPor argument is required");
        EntityManager em = Pedido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Pedido AS o WHERE o.recibidoPor = :recibidoPor", Long.class);
        q.setParameter("recibidoPor", recibidoPor);
        return ((Long) q.getSingleResult());
    }

    public static List<Pedido> findPedidoesByRecibidoPor(Usuario recibidoPor) {
        if (recibidoPor == null) throw new IllegalArgumentException("The recibidoPor argument is required");
        EntityManager em = Pedido.entityManager();
        return em.createQuery("SELECT o FROM Pedido AS o WHERE o.recibidoPor = :recibidoPor", Pedido.class).setParameter("recibidoPor", recibidoPor).getResultList();
    }

    public static List<Pedido> findPedidoesByRecibidoPor(int firstResult, int maxResults, Usuario recibidoPor, String sortFieldName, String sortOrder) {
        if (recibidoPor == null) throw new IllegalArgumentException("The recibidoPor argument is required");
        EntityManager em = Pedido.entityManager();
        String jpaQuery = "SELECT o FROM Pedido AS o WHERE o.recibidoPor = :recibidoPor";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Pedido.class).setParameter("recibidoPor", recibidoPor).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Autowired
    private transient JavaMailSender mailTemplate;

    public void sendMessage(final String mailFrom, final String subject, final String mailTo, final String content) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mailTo);
                message.setFrom(mailFrom); // could be parameterized...
                message.setText(content, true);
                message.setSubject(subject);
            }
        };
        mailTemplate.send(preparator);
    }
}
