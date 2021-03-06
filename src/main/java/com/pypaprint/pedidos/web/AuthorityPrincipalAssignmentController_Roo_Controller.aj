// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.web;

import com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment;
import com.pypaprint.pedidos.services.AuthorityPrincipalAssignmentService;
import com.pypaprint.pedidos.web.AuthorityPrincipalAssignmentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect AuthorityPrincipalAssignmentController_Roo_Controller {
    
    @Autowired
    AuthorityPrincipalAssignmentService AuthorityPrincipalAssignmentController.authorityPrincipalAssignmentService;
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String AuthorityPrincipalAssignmentController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("authorityprincipalassignment", authorityPrincipalAssignmentService.findAuthorityPrincipalAssignment(id));
        uiModel.addAttribute("itemId", id);
        return "authorityprincipalassignments/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String AuthorityPrincipalAssignmentController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("authorityprincipalassignments", AuthorityPrincipalAssignment.findAuthorityPrincipalAssignmentEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) authorityPrincipalAssignmentService.countAllAuthorityPrincipalAssignments() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("authorityprincipalassignments", AuthorityPrincipalAssignment.findAllAuthorityPrincipalAssignments(sortFieldName, sortOrder));
        }
        return "authorityprincipalassignments/list";
    }
    
}
