package com.pypaprint.pedidos.utils.seeders;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pypaprint.pedidos.domain.StatusPedido;
import com.pypaprint.pedidos.domain.security.Authority;
import com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment;
import com.pypaprint.pedidos.domain.security.Usuario;
import com.pypaprint.pedidos.utils.enumerations.Statuses;


@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {
	private final Log log = LogFactory.getLog(DataSeeder.class);

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("<<Iniciando carga de data>>");
		/*if (event.getApplicationContext().getParent() == null) { // root context
            log.info("event.getApplicationContext() = " + event.getApplicationContext());
            String runMode = "server";
            try {
                Resource resource = new ClassPathResource("/env.properties");
                Properties props = PropertiesLoaderUtils.loadProperties(resource);
                runMode = props.getProperty("run.mode");
            } catch (IOException ignored) {
            }
            log.info("run.mode = " + runMode);
            if ("server".equals(runMode)) {
            	//Creando usuario administrador del sistema.
            	log.info("Creando el usuario administrador");
            	Usuario principal = new Usuario();
        		principal.setPrimerNombre("Jorge");
        		principal.setPrimerApellido("Mir");
        		principal.setSegundoNombre("Augusto");
        		principal.setSegundoApellido("Seijas");
        		principal.setUsername("admin");
        		principal.setPassword("admin");
        		principal.setCorreo("tapiasnora@hotmail.com");
        		principal.setTelefono("+584141275892");
        		principal.setEnabled(true);
        		principal.persist();
        		
        		log.info("Crando rol administrador");
        		Authority authority = new Authority();        		
        		authority.setNombreDeRol("ROLE_MAESTRO");
        		authority.persist();
        		
        		log.info("Asociando rol administrador al usuario creado");
        		AuthorityPrincipalAssignment apa = new AuthorityPrincipalAssignment();
        		apa.setPermiso(authority);
        		apa.setUsername(principal);
        		apa.persist();
        		
        		
        		Usuario user = new Usuario();
        		user.setPrimerNombre("Usuario");
        		user.setPrimerApellido("Normal");
        		user.setSegundoNombre("");
        		user.setSegundoApellido("");
        		user.setUsername("user");
        		user.setPassword("user");
        		user.setCorreo("user@gmail.com");
        		user.setTelefono("+50760456514");
        		user.setEnabled(true);
        		user.persist();
        		
        		log.info("Crando rol administrador");
        		Authority rolUser = new Authority();        		
        		rolUser.setNombreDeRol("ROLE_USER");
        		rolUser.persist();
        		
        		log.info("Asociando rol administrador al usuario creado");
        		AuthorityPrincipalAssignment apa2 = new AuthorityPrincipalAssignment();
        		apa2.setPermiso(rolUser);
        		apa2.setUsername(user);
        		apa2.persist();
        		
        		rolUser = new Authority();        		
        		rolUser.setNombreDeRol("ROLE_PRODUCCION");
        		rolUser.persist();
        		
        		rolUser = new Authority();        		
        		rolUser.setNombreDeRol("ROLE_IMPRENTA");
        		rolUser.persist();
        		
        		rolUser = new Authority();        		
        		rolUser.setNombreDeRol("ROLE_DISENO");
        		rolUser.persist();
        		//Fin creacion del usuario administrador del sistema.
        		
        		StatusPedido status = new StatusPedido();
        		status.setStatus("Orden recibida");
        		status.setStatus_propertie(Statuses.ORDEN_RECIBIDA.name());
        		status.persist();
        		status = new StatusPedido();
        		status.setStatus("Orden en produccion");
        		status.setStatus_propertie(Statuses.ORDEN_EN_PRODUCCION.name());
        		status.persist();
        		status = new StatusPedido();
        		status.setStatus("Orden en produccion Boleita");
        		status.setStatus_propertie(Statuses.ORDEN_EN_PRODUCCION_BOLEITA.name());
        		status.persist();
        		status = new StatusPedido();
        		status.setStatus("Pedido terminado");
        		status.setStatus_propertie(Statuses.PEDIDO_TERMINADO.name());
        		status.persist();
        		status = new StatusPedido();
        		status.setStatus("Pedido listo para retirar");
        		status.setStatus_propertie(Statuses.PEDIDO_LISTO_PARA_RETIRAR.name());
        		status.persist();
        		status = new StatusPedido();
        		status.setStatus("Entregado");
        		status.setStatus_propertie(Statuses.ENTREGADO.name());
        		status.persist();
        		status = new StatusPedido();
        		status.setStatus("Orden en diseno");
        		status.setStatus_propertie(Statuses.ORDEN_EN_DISENO.name());
        		status.persist();
        		
            }
        }*/
		log.info("<<Finalizo la carga de data>>");
	}
	

}
