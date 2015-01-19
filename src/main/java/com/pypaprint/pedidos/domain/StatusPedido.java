package com.pypaprint.pedidos.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJson(deepSerialize = true)
@RooJpaActiveRecord(finders = { "findStatusPedidoesByStatusEquals", "findStatusPedidoesByStatus_propertieEquals" })
public class StatusPedido {

    /**
     */
    @NotNull
    @Column(unique = true)
    private String status;

    /**
     */
    @NotNull
    @Column(unique = true)
    private String status_propertie;
}
