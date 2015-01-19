package com.pypaprint.pedidos;
import com.pypaprint.pedidos.domain.Cliente;
import com.pypaprint.pedidos.domain.Pedido;
import com.pypaprint.pedidos.domain.PedidoProductos;
import com.pypaprint.pedidos.domain.Producto;
import com.pypaprint.pedidos.domain.StatusPedido;
import com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment;
import com.pypaprint.pedidos.domain.security.Usuario;
import com.pypaprint.pedidos.services.ClienteService;
import com.pypaprint.pedidos.services.PedidoProductosService;
import com.pypaprint.pedidos.services.PedidoService;
import com.pypaprint.pedidos.services.ProductoService;
import com.pypaprint.pedidos.services.StatusPedidoService;
import com.pypaprint.pedidos.services.UsuarioService;
import com.pypaprint.pedidos.utils.MessageTemplate;
import com.pypaprint.pedidos.utils.enumerations.Statuses;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.JavaScriptUtils;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RooWebJson(jsonObject = Pedido.class)
@Controller
@RequestMapping("/pedidoes")
@RooWebScaffold(path = "pedidoes", formBackingObject = Pedido.class)
@RooWebFinder
public class PedidoController {

    @Autowired
    PedidoService pedidoService;
    
    @Autowired
    PedidoProductosService pedidoproductosService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProductoService productoService;

    @Autowired
    StatusPedidoService statusPedidoService;

    @Autowired
    UsuarioService usuarioService;

    @Resource(name = "getEmail")
    private Properties getEmail;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Pedido pedido = pedidoService.findPedido(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (pedido == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(pedido.toJson(), headers, HttpStatus.OK);
    }

    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Pedido> result = pedidoService.findAllPedidoes();
        return new ResponseEntity<String>(Pedido.toJsonArray(result), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{isCliente}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, @PathVariable("isCliente") boolean isCliente, UriComponentsBuilder uriBuilder) {
        MessageTemplate msjTemp = new MessageTemplate();
        Pedido pedido = Pedido.fromJsonToPedido(json);
        Cliente cliente = pedido.getCliente();
        Set<Producto> productos = pedido.getProductos();
        Set<Producto> newProductos = new HashSet<Producto>();
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto product = iterator.next();
            Producto productFound = productoService.findProducto(product.getId());
            //PedidoProductos newPedidoProducto
            productFound.setCantidadPedido(product.getCantidadPedido());
            newProductos.add(productFound);
        }
        pedido.setProductos(null);
        pedido.setProductos(newProductos);
        Usuario user = pedido.getRecibidoPor();
        Usuario usuario = usuarioService.findUsuario(user.getId());
        StatusPedido status = pedido.getStatus();
        StatusPedido stat = statusPedidoService.findStatusPedido(status.getId());
        pedido.setRecibidoPor(usuario);
        pedido.setStatus(stat);
        //save cliente
        if (!isCliente) {
            clienteService.saveCliente(cliente);
        } else {
            Cliente newCliente = clienteService.findCliente(cliente.getId());
            pedido.setCliente(newCliente);
        }
        try {
            pedidoService.savePedido(pedido);
            iterator  = newProductos.iterator();
            String cadenaProductos = "";
            while (iterator.hasNext()) {
                Producto product = iterator.next();
                PedidoProductos newPedidoProducto = new PedidoProductos();
                newPedidoProducto.setProducto(product);
                newPedidoProducto.setPedido(pedido);
                newPedidoProducto.setCantidad(product.getCantidadPedido());
                pedidoproductosService.savePedidoProductos(newPedidoProducto);
                if (iterator.hasNext()) {
           			cadenaProductos += product.getCantidadPedido() + " " + product.getNombre() +  ", ";
           		} else {
           			cadenaProductos += product.getCantidadPedido() + " " + product.getNombre();
           		}
            }
            String stat_prop = stat.getStatus_propertie();
            String conten = getEmail.getProperty("cliente." + stat_prop);
            String contenEmp = getEmail.getProperty("usuario." + stat_prop);
            Map<String, String> params = msjTemp.parametrosCorreo(stat_prop, cliente, pedido, usuario, getEmail, cadenaProductos);
            msjTemp.setParametrosConten(params, conten, contenEmp, stat_prop);
            pedido.sendMessage(usuario.getCorreo(), "Pedido Nº " + pedido.getId(), cliente.getEmail(), msjTemp.getMessage());
            pedido.sendMessage(usuario.getCorreo(), "Pedido Nº " + pedido.getId(), usuario.getCorreo(), msjTemp.getMessageEmp());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
            headers.add("Location", uriBuilder.path(a.value()[0] + "/" + pedido.getId().toString()).build().toUriString());
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            System.err.println(ex);
            return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Pedido pedido : Pedido.fromJsonArrayToPedidoes(json)) {
            pedidoService.savePedido(pedido);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Pedido pedido = Pedido.fromJsonToPedido(json);
        pedido.setId(id);
        if (pedidoService.updatePedido(pedido) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Pedido pedido = pedidoService.findPedido(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (pedido == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        pedidoService.deletePedido(pedido);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteproducto/{id}", method = RequestMethod.GET, headers = "text/html")
    public String deleteProductoPedido(@PathVariable("id") String id, HttpServletRequest httpServletRequest) {
    	String[] ids = id.split("-");
    	Long idPedProd = Long.parseLong(ids[0]);
    	Long idPed = Long.parseLong(ids[1]);
    	PedidoProductos pedidoProd = new PedidoProductos();
    	pedidoProd.setId(idPedProd);
    	pedidoproductosService.deletePedidoProductos(pedidoProd);
        return "redirect:/pedidoes/" + encodeUrlPathSegment(idPed.toString() + "?form", httpServletRequest);
    }
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Pedido pedido, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, pedido);
            return "pedidoes/create";
        }
        uiModel.asMap().clear();
        pedidoService.savePedido(pedido);
        return "redirect:/pedidoes/" + encodeUrlPathSegment(pedido.getId().toString(), httpServletRequest);
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario recibidoPor = Usuario.findUsuariosByUsernameEquals(usuario.getUsername()).getSingleResult();
        AuthorityPrincipalAssignment auth = AuthorityPrincipalAssignment.findAuthorityPrincipalAssignmentsByUsername(recibidoPor).getSingleResult();
        String rol = auth.getPermiso().getNombreDeRol();
        if (rol.equalsIgnoreCase("ROLE_MAESTRO") || rol.equalsIgnoreCase("ROLE_PRODUCCION")) {
            if (page != null || size != null) {
                int sizeNo = size == null ? 10 : size.intValue();
                final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
                uiModel.addAttribute("pedidoes", Pedido.findPedidoEntries(firstResult, sizeNo, sortFieldName, sortOrder));
                float nrOfPages = (float) pedidoService.countAllPedidoes() / sizeNo;
                uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            } else {
                uiModel.addAttribute("pedidoes", Pedido.findAllPedidoes(sortFieldName, sortOrder));
            }
        } else {
            if (page != null || size != null) {
                int sizeNo = size == null ? 10 : size.intValue();
                final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
                uiModel.addAttribute("pedidoes", Pedido.findPedidoesByRecibidoPor(firstResult, sizeNo, recibidoPor, sortFieldName, sortOrder));
                float nrOfPages = (float) pedidoService.countAllPedidoes() / sizeNo;
                uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            } else {
                uiModel.addAttribute("pedidoes", Pedido.findAllPedidoes(sortFieldName, sortOrder));
            }
        }
        addDateTimeFormatPatterns(uiModel);
        return "pedidoes/list";
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Pedido());
        User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario recibidoPor = Usuario.findUsuariosByUsernameEquals(usuario.getUsername()).getSingleResult();
        uiModel.addAttribute("recibidoPor", recibidoPor.getUsername());
        uiModel.addAttribute("recibidoPorId", recibidoPor.getId());
        StatusPedido status = StatusPedido.findStatusPedidoesByStatus_propertieEquals(Statuses.ORDEN_RECIBIDA.name()).getSingleResult();
        uiModel.addAttribute("status", status.getStatus());
        uiModel.addAttribute("statusId", status.getId());
        uiModel.addAttribute("productos", productoService.findAllProductoes());
        return "pedidoes/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        Pedido pedid = pedidoService.findPedido(id);
        PedidoProductos pedprod = new PedidoProductos();
        Set<Producto> newProductos = new HashSet<Producto>();
       	List <PedidoProductos> list = pedprod.findPedidoProductosesByPedido(pedid).getResultList();
       	Iterator<PedidoProductos> iter = list.iterator();
       	String cadenaProductos = "";
       	while (iter.hasNext()) {
       		PedidoProductos pedidproduc = iter.next();
       		Producto prod  = pedidproduc.getProducto();
       		prod.setCantidadPedido(pedidproduc.getCantidad());
       		newProductos.add(prod);
       		if (iter.hasNext()) {
       			cadenaProductos += prod.getCantidadPedido() + " " + prod.getNombre() +  "<br/>";
       		} else {
       			cadenaProductos += prod.getCantidadPedido() + " " + prod.getNombre();
       		}
       	}
       	
       	pedid.setProductos(newProductos);
        uiModel.addAttribute("pedido", pedid);
        uiModel.addAttribute("productos", cadenaProductos);
        uiModel.addAttribute("itemId", id);
        return "pedidoes/show";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Pedido pedido, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, pedido);
            return "pedidoes/update";
        }
        uiModel.asMap().clear();
        pedidoService.updatePedido(pedido);
        return "redirect:/pedidoes/" + encodeUrlPathSegment(pedido.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    	addDateTimeFormatPatterns(uiModel);
    	Pedido pedid = pedidoService.findPedido(id);
        PedidoProductos pedprod = new PedidoProductos();
        Set<Producto> newProductos = new HashSet<Producto>();
       	List <PedidoProductos> list = pedprod.findPedidoProductosesByPedido(pedid).getResultList();
       	Iterator<PedidoProductos> iter = list.iterator();
       	String cadenaProductos = "<table>";
       	int line = 1;
       	String color = "";
       	while (iter.hasNext()) {
       		if (line == 1) {
       			color = "#EFEFEF";
       			line = 0;
       		} else {
       			color = "white";
       			line = 1;
       		}
       		PedidoProductos pedidproduc = iter.next();
       		Producto prod  = pedidproduc.getProducto();
       		prod.setCantidadPedido(pedidproduc.getCantidad());
       		newProductos.add(prod);
       		if (iter.hasNext()) {
       			cadenaProductos += "<tr style='width:150px;'><td style='background-color:" +
       					color + "; margin-right: 10px;' id= '" + prod.getId() + "'>"
       					+ prod.getCantidadPedido() + " " + prod.getNombre()
       					+  "<a href='/pypaprint/pedidoes/deleteproducto/" + id + "' style='color: green; background-color: " + color + ";float:right;'>"
       					+ "<b>X</b></a></td></tr>";
       		} else {
       			cadenaProductos += "<tr  style='width:150px;'><td style='background-color:" + color + ";margin-right: 10px;' id= '" + prod.getId() + "'>" +prod.getCantidadPedido() + " " + prod.getNombre() +  "<a href='#' style='color: green; background-color: " + color + ";float:right;'><b>X</b></a></td></tr></table>";
       		}
       	}
       	
       	pedid.setProductos(newProductos);
        uiModel.addAttribute("pedido", pedid);
        uiModel.addAttribute("cliente", pedid.getCliente().getNombre() + " " + pedid.getCliente().getApellido() + " " + pedid.getCliente().getEmail());
        uiModel.addAttribute("productos", cadenaProductos);
        uiModel.addAttribute("statuspedidoes", statusPedidoService.findAllStatusPedidoes());
        uiModel.addAttribute("itemId", id);
        return "pedidoes/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Pedido pedido = pedidoService.findPedido(id);
        pedidoService.deletePedido(pedido);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/pedidoes";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("pedido_fechaderecepcion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("pedido_fechapropuestadeentrega_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    void populateEditForm(Model uiModel, Pedido pedido) {
        uiModel.addAttribute("pedido", pedido);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("clientes", clienteService.findAllClientes());
        uiModel.addAttribute("productoes", productoService.findAllProductoes());
        uiModel.addAttribute("statuspedidoes", statusPedidoService.findAllStatusPedidoes());
        uiModel.addAttribute("usuarios", usuarioService.findAllUsuarios());
    }

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
        }
        return pathSegment;
    }

    @RequestMapping(params = "find=ByRecibidoPor", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPedidoesByRecibidoPor(@RequestParam("recibidoPor") Usuario recibidoPor) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Pedido.toJsonArray(Pedido.findPedidoesByRecibidoPor(recibidoPor)), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/ByCliente", params = "form", method = RequestMethod.GET)
    public String findPedidoesByClienteForm(Model uiModel) {
        uiModel.addAttribute("clientes", clienteService.findAllClientes());
        return "pedidoes/findPedidoesByCliente";
    }

    @RequestMapping(value = "/ByCliente", method = RequestMethod.GET)
    public String findPedidoesByCliente(@RequestParam("cliente") Cliente cliente, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("pedidoes", Pedido.findPedidoesByCliente(cliente, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Pedido.countFindPedidoesByCliente(cliente) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("pedidoes", Pedido.findPedidoesByCliente(cliente, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "pedidoes/list";
    }

    @RequestMapping(params = { "find=ByRecibidoPor", "form" }, method = RequestMethod.GET)
    public String findPedidoesByRecibidoPorForm(Model uiModel) {
        uiModel.addAttribute("usuarios", usuarioService.findAllUsuarios());
        return "pedidoes/findPedidoesByRecibidoPor";
    }

    @RequestMapping(params = "find=ByRecibidoPor", method = RequestMethod.GET)
    public String findPedidoesByRecibidoPor(@RequestParam("recibidoPor") Usuario recibidoPor, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("pedidoes", Pedido.findPedidoesByRecibidoPor(recibidoPor, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Pedido.countFindPedidoesByRecibidoPor(recibidoPor) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("pedidoes", Pedido.findPedidoesByRecibidoPor(recibidoPor, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "pedidoes/list";
    }

    @RequestMapping(value = "/ByPedidoId", params = "form", method = RequestMethod.GET)
    public String findPedidoesById(Model uiModel) {
        return "pedidoes/findPedidoesById";
    }

    @RequestMapping(value = "/ByPedidoId", params = "find=ByRecibidoPor", method = RequestMethod.GET)
    public String findPedidoesById(@RequestParam("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("pedido", pedidoService.findPedido(id));
        uiModel.addAttribute("itemId", id);
        return "pedidoes/show";
    }

    @RequestMapping(value = "/ByStatus", params = "form", method = RequestMethod.GET)
    public String findPedidoesByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuspedidoes", statusPedidoService.findAllStatusPedidoes());
        return "pedidoes/findPedidoesByStatus";
    }

    @RequestMapping(value = "/ByStatus", method = RequestMethod.GET)
    public String findPedidoesByStatus(@RequestParam("status") StatusPedido status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("pedidoes", Pedido.findPedidoesByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Pedido.countFindPedidoesByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("pedidoes", Pedido.findPedidoesByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "pedidoes/list";
    }
}
