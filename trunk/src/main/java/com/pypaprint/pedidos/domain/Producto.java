package com.pypaprint.pedidos.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.json.RooJson;

import com.pypaprint.pedidos.domain.security.Usuario;

@RooJavaBean
@RooToString
@RooJson(deepSerialize = true)
@RooJpaActiveRecord(finders = { "findProductoesByCodigoEquals" })
public class Producto {

    /**
     */
    @NotNull
    private String nombre;

    /**
     */
    @NotNull
    @Size(max = 150)
    private String descripcion;

    /**
     */
    private Double precio;

    /**
     */
    @Column(nullable=true)
    private Long cantidad;

    /**
     */
    private Double duracionEnProduccion;

    /**
     */
    @NotNull
    private String codigo;
    
    @Column(nullable=true)
    private Double cantidadPedido;

	public Double getCantidadPedido() {
		return cantidadPedido;
	}

	public void setCantidadPedido(Double cantidadPedido) {
		this.cantidadPedido = cantidadPedido;
	}
    
    
}
