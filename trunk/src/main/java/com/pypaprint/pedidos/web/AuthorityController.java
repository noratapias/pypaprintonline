package com.pypaprint.pedidos.web;
import com.pypaprint.pedidos.domain.security.Authority;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/authoritys")
@Controller
@RooWebScaffold(path = "authoritys", formBackingObject = Authority.class, create = false, delete = false, update = false, exposeFinders = false, exposeJson = false, populateMethods = false)
@RooWebJson(jsonObject = Authority.class)
public class AuthorityController {
}
