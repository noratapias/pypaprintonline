package com.pypaprint.pedidos.domain.security;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.pypaprint.pedidos.utils.enumerations.Sexo;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJson(deepSerialize = true)
@RooJpaActiveRecord(finders = { "findUsuariosByUsernameEquals" })
public class Usuario {

    @NotNull
    private String primerNombre;

    private String segundoNombre;

    @NotNull
    private String primerApellido;

    private String segundoApellido;

    @Column(unique = true)
    @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$", message = "Ingrese un correo valido. Ejemplo: admin@gmail.com")
    private String correo;

    @Pattern(regexp = "^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$", message = "Ingrese un telefono valido. Ejemplpo +50760456510")
    private String telefono;

    private Sexo sexo;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaDeNacimiento;

    @Size(min = 10, max = 50)
    private String direccion;

    private String departamento;

    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 3, max = 50)
    private String password;

    private Boolean enabled;
}
