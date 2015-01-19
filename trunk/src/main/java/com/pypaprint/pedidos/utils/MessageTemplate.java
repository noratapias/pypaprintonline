package com.pypaprint.pedidos.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.pypaprint.pedidos.domain.Cliente;
import com.pypaprint.pedidos.domain.Pedido;
import com.pypaprint.pedidos.domain.security.Usuario;

public class MessageTemplate {
	private String message;
	
	private String messageEmp;
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
	
	public void setMessageEmp(String messageEmp) {
		this.messageEmp = messageEmp;
	}
	public String getMessageEmp() {
		return this.messageEmp;
	}
	public Map<String, String> parametrosCorreo (String status, Cliente cliente, Pedido pedido, Usuario usuario, Properties getEmail, String productos) {
    	Map params = new HashMap();
    	Formatos formatos = new Formatos();
        	//cliente
        	params.put("(*clienteNombre*)", cliente.getNombre() + " " + cliente.getApellido());
        	params.put("(*pedidoId*)", String.valueOf(pedido.getId()));
        	params.put("(*numeroPypa*)", getEmail.getProperty("pypa.numero"));
        	params.put("(*correoPypa*)", getEmail.getProperty("pypa.correo"));
        	params.put("(*productos*)", productos);
        	params.put("(*fecharecepcion*)", formatos.parseDateToString(pedido.getFechaDeRecepcion(), "dd/MM/yyy"));
        	params.put("(*abono*)", String.valueOf(pedido.getAbono()));
        	params.put("(*total*)", String.valueOf(pedido.getTotalBs()));
        	params.put("(*recibidopor*)", usuario.getPrimerNombre() + " " + usuario.getPrimerApellido());
        	params.put("(*status*)", status.replace("_", " "));
        	//usuario
        	params.put("(*usuarioNombre*)", usuario.getPrimerNombre() + " " + usuario.getPrimerApellido());
        	params.put("(*correoCliente*)", cliente.getEmail());
        	params.put("(*fechaEntrega*)", formatos.parseDateToString(pedido.getFechaPropuestaDeEntrega(), "dd/MM/yyy"));
       
        return params;
    }
	public void setParametrosConten(Map<String, String> params, String contentCli, String contentEmp,
			String status) {
			String client =  params.get("(*clienteNombre*)");
			String idPed = params.get("(*pedidoId*)");
			String numPyp = params.get("(*numeroPypa*)");
			String correoPypa = params.get("(*correoPypa*)");
			String usuarioNombre = params.get("(*usuarioNombre*)");
			String correoCliente = params.get("(*correoCliente*)");
			String fechaEntrega = params.get("(*fechaEntrega*)");
			String productos = params.get("(*productos*)");
			String fecharecepcion = params.get("(*fecharecepcion*)");
			String abono = params.get("(*abono*)");
			String total = params.get("(*total*)");
			String recibidopor = params.get("(*recibidopor*)");
			String stat = params.get("(*status*)");
			

			//pedido

			contentCli = contentCli.replace("(*productos*)", productos);
			contentEmp = contentEmp.replace("(*productos*)", productos);
			contentCli = contentCli.replace("(*fecharecepcion*)", fecharecepcion);
			contentEmp = contentEmp.replace("(*fecharecepcion*)", fecharecepcion);
			contentCli = contentCli.replace("(*abono*)", abono);
			contentEmp = contentEmp.replace("(*abono*)", abono);
			contentCli = contentCli.replace("(*total*)", total);
			contentEmp = contentEmp.replace("(*total*)", total);
			contentCli = contentCli.replace("(*recibidopor*)", recibidopor);
			contentEmp = contentEmp.replace("(*recibidopor*)", recibidopor);
			contentCli = contentCli.replace("(*status*)", stat);
			contentEmp = contentEmp.replace("(*status*)", stat);
			contentEmp = contentEmp.replace("(*clienteNombre*)", client);
			
			
			contentCli = contentCli.replace("(*clienteNombre*)", client);
			contentCli = contentCli.replace("(*pedidoId*)", idPed);
			contentCli = contentCli.replace("(*numeroPypa*)", numPyp);
			contentCli = contentCli.replace("(*correoPypa*)", correoPypa);

			contentEmp = contentEmp.replace("(*usuarioNombre*)", usuarioNombre);
			contentEmp = contentEmp.replace("(*pedidoId*)", idPed);
			contentEmp = contentEmp.replace("(*correoCliente*)", correoCliente);
			contentEmp = contentEmp.replace("(*fechaEntrega*)", fechaEntrega);
		
		this.setMessage(contentCli);
		this.setMessageEmp(contentEmp);
		
	}
}
