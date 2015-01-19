package com.pypaprint.pedidos;
import com.pypaprint.pedidos.domain.security.Usuario;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RooWebJson(jsonObject = Usuario.class)
@Controller
@RequestMapping("/usuarios")
@RooWebScaffold(path = "usuarios", formBackingObject = Usuario.class)
@RooWebFinder
public class UsuarioController {
}
