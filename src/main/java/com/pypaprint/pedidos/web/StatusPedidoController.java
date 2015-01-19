package com.pypaprint.pedidos.web;
import com.pypaprint.pedidos.domain.StatusPedido;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/statuspedidoes")
@Controller
@RooWebScaffold(path = "statuspedidoes", formBackingObject = StatusPedido.class)
@RooWebJson(jsonObject = StatusPedido.class)
public class StatusPedidoController {
}
