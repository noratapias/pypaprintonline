package com.pypaprint.pedidos.web.security;
import com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/security/assignments")
@Controller
@RooWebScaffold(path = "security/assignments", formBackingObject = AuthorityPrincipalAssignment.class)
public class RoleMappingController {
}
