package com.pypaprint.pedidos.web;
import com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/authorityprincipalassignments")
@Controller
@RooWebScaffold(path = "authorityprincipalassignments", formBackingObject = AuthorityPrincipalAssignment.class, create = false, delete = false, update = false, exposeFinders = false, exposeJson = false)
@RooWebJson(jsonObject = AuthorityPrincipalAssignment.class)
@RooWebFinder
public class AuthorityPrincipalAssignmentController {
}
