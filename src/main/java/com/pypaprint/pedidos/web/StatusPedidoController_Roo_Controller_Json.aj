// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.web;

import com.pypaprint.pedidos.domain.StatusPedido;
import com.pypaprint.pedidos.web.StatusPedidoController;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

privileged aspect StatusPedidoController_Roo_Controller_Json {
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> StatusPedidoController.showJson(@PathVariable("id") Long id) {
        StatusPedido statusPedido = statusPedidoService.findStatusPedido(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (statusPedido == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(statusPedido.toJson(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> StatusPedidoController.listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<StatusPedido> result = statusPedidoService.findAllStatusPedidoes();
        return new ResponseEntity<String>(StatusPedido.toJsonArray(result), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> StatusPedidoController.createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        StatusPedido statusPedido = StatusPedido.fromJsonToStatusPedido(json);
        statusPedidoService.saveStatusPedido(statusPedido);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+statusPedido.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> StatusPedidoController.createFromJsonArray(@RequestBody String json) {
        for (StatusPedido statusPedido: StatusPedido.fromJsonArrayToStatusPedidoes(json)) {
            statusPedidoService.saveStatusPedido(statusPedido);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> StatusPedidoController.updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        StatusPedido statusPedido = StatusPedido.fromJsonToStatusPedido(json);
        statusPedido.setId(id);
        if (statusPedidoService.updateStatusPedido(statusPedido) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> StatusPedidoController.deleteFromJson(@PathVariable("id") Long id) {
        StatusPedido statusPedido = statusPedidoService.findStatusPedido(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (statusPedido == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        statusPedidoService.deleteStatusPedido(statusPedido);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
}
