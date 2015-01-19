package com.pypaprint.pedidos.web.security;
import com.pypaprint.pedidos.domain.security.Authority;
import com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment;
import com.pypaprint.pedidos.domain.security.Usuario;
import com.pypaprint.pedidos.services.UsuarioService;
import com.pypaprint.pedidos.utils.enumerations.Sexo;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/security/users")
@Controller
@RooWebScaffold(path = "security/users", formBackingObject = Usuario.class)
@RooWebJson(jsonObject = Usuario.class)
@RooWebFinder
public class UserController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Usuario usuario, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, @RequestParam(value = "rol", required = true) String rol) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, usuario);
            return "security/users/create";
        }
        uiModel.asMap().clear();
        usuario.persist();
        AuthorityPrincipalAssignment ah = new AuthorityPrincipalAssignment();
        ah.setPermiso(Authority.findAuthority(new Long(rol)));
        ah.setUsername(usuario);
        ah.persist();
        return "redirect:/security/users/" + encodeUrlPathSegment(usuario.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Usuario());
        return "security/users/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        Usuario usuario = Usuario.findUsuario(id);
        uiModel.addAttribute("usuario", usuarioService.findUsuario(id));
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("rol", AuthorityPrincipalAssignment.findAuthorityPrincipalAssignmentsByUsername(usuario).getSingleResult().getPermiso().getNombreDeRol());
        return "security/users/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("usuarios", Usuario.findUsuarioEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) usuarioService.countAllUsuarios() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("usuarios", Usuario.findAllUsuarios(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "security/users/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Usuario usuario, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, @RequestParam(value = "rol", required = true) String rol) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, usuario);
            return "security/users/update";
        }
        uiModel.asMap().clear();
        usuario.merge();
        AuthorityPrincipalAssignment ah = AuthorityPrincipalAssignment.findAuthorityPrincipalAssignmentsByUsername(usuario).getSingleResult();
        ah.setPermiso(Authority.findAuthority(new Long(rol)));
        ah.merge();
        return "redirect:/security/users/" + encodeUrlPathSegment(usuario.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, usuarioService.findUsuario(id));
        return "security/users/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Usuario usuario = usuarioService.findUsuario(id);
        usuarioService.deleteUsuario(usuario);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/security/users";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("usuario_fechadenacimiento_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    void populateEditForm(Model uiModel, Usuario usuario) {
        uiModel.addAttribute("usuario", usuario);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("sexoes", Arrays.asList(Sexo.values()));
        uiModel.addAttribute("authoritys", Authority.findAllAuthoritys());
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
}
