// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.domain;

import com.pypaprint.pedidos.domain.StatusPedido;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect StatusPedido_Roo_Json {
    
    public String StatusPedido.toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }
    
    public String StatusPedido.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }
    
    public static StatusPedido StatusPedido.fromJsonToStatusPedido(String json) {
        return new JSONDeserializer<StatusPedido>()
        .use(null, StatusPedido.class).deserialize(json);
    }
    
    public static String StatusPedido.toJsonArray(Collection<StatusPedido> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }
    
    public static String StatusPedido.toJsonArray(Collection<StatusPedido> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }
    
    public static Collection<StatusPedido> StatusPedido.fromJsonArrayToStatusPedidoes(String json) {
        return new JSONDeserializer<List<StatusPedido>>()
        .use("values", StatusPedido.class).deserialize(json);
    }
    
}
