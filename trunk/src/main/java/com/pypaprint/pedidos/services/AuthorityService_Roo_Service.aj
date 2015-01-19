// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.services;

import com.pypaprint.pedidos.domain.security.Authority;
import com.pypaprint.pedidos.services.AuthorityService;
import java.util.List;

privileged aspect AuthorityService_Roo_Service {
    
    public abstract long AuthorityService.countAllAuthoritys();    
    public abstract void AuthorityService.deleteAuthority(Authority authority);    
    public abstract Authority AuthorityService.findAuthority(Long id);    
    public abstract List<Authority> AuthorityService.findAllAuthoritys();    
    public abstract List<Authority> AuthorityService.findAuthorityEntries(int firstResult, int maxResults);    
    public abstract void AuthorityService.saveAuthority(Authority authority);    
    public abstract Authority AuthorityService.updateAuthority(Authority authority);    
}
