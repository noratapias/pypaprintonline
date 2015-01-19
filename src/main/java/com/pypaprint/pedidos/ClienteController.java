package com.pypaprint.pedidos;
import com.pypaprint.pedidos.domain.Cliente;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RooWebJson(jsonObject = Cliente.class)
@Controller
@RequestMapping("/clientes")
@RooWebScaffold(path = "clientes", formBackingObject = Cliente.class)
@RooWebFinder
public class ClienteController {
}
