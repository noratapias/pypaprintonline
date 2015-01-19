package com.pypaprint.pedidos.scheduler;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import com.pypaprint.pedidos.domain.Cliente;
import com.pypaprint.pedidos.domain.Pedido;
import com.pypaprint.pedidos.domain.PedidoProductos;
import com.pypaprint.pedidos.domain.Producto;
import com.pypaprint.pedidos.domain.security.Usuario;
import com.pypaprint.pedidos.utils.MessageTemplate;
import com.pypaprint.pedidos.utils.enumerations.Statuses;

public class MessageTask {
	@Resource(name = "getEmail")
    private Properties getEmail;
	public void consultarPedidos(Pedido pedido) {
		Date fechaEntrega = pedido.getFechaPropuestaDeEntrega();
		System.out.println(fechaEntrega);
		Date today = new Date();
		//if (!pedido.getStatus().getStatus_propertie().equalsIgnoreCase(Statuses.ENTREGADO))
		//if (today.compareTo(fechaEntrega) == 0 && today.after(fechaEntrega)) {
			String stat_prop = pedido.getStatus().getStatus_propertie();
			Cliente cliente = pedido.getCliente();
			Usuario usuario = pedido.getRecibidoPor();
			PedidoProductos pedprod = new PedidoProductos();
	       	List <PedidoProductos> list = pedprod.findPedidoProductosesByPedido(pedido).getResultList();
	       	Iterator<PedidoProductos> iter = list.iterator();
	       	String cadenaProductos = "";
	       	while (iter.hasNext()) {
	       		PedidoProductos pedidproduc = iter.next();
	       		Producto prod  = pedidproduc.getProducto();
	       		prod.setCantidadPedido(pedidproduc.getCantidad());
	       		if (iter.hasNext()) {
	       			cadenaProductos += prod.getCantidadPedido() + " " + prod.getNombre() +  ", ";
	       		} else {
	       			cadenaProductos += prod.getCantidadPedido() + " " + prod.getNombre();
	       		}
	       	}
			MessageTemplate msjTemp = new MessageTemplate();
            String contenEmp = getEmail.getProperty("usuario.VENCIMIENTO");
            Map<String, String> params = msjTemp.parametrosCorreo(stat_prop, cliente, pedido, usuario, getEmail, cadenaProductos);
            msjTemp.setParametrosConten(params, "", contenEmp, stat_prop);
			pedido.sendMessage(pedido.getRecibidoPor().getCorreo(),
					"Pedido Nº " +  pedido.getId() +  " cerca de su fecha de vencimiento",
					pedido.getRecibidoPor().getCorreo(), msjTemp.getMessageEmp());
		//}
	}
}
