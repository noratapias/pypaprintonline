package com.pypaprint.pedidos.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJson(deepSerialize = true)
@RooJpaActiveRecord(finders = { "findClientesByEmailEquals" })
public class Cliente {

    /**
     */
    @NotNull
    private String nombre;

    /**
     */
    @NotNull
    private String apellido;

    /**
     */
    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Ingrese un correo valido. Ejemplo: admin@gmail.com")
    private String email;

    /**
     */
    @NotNull
    @Pattern(regexp = "^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$", message = "Ingrese un telefono valido. Ejemplpo +582760456510")
    private String telefono;
}
