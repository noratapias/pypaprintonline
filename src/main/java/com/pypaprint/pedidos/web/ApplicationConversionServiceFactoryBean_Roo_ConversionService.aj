// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.web;

import com.pypaprint.pedidos.domain.Cliente;
import com.pypaprint.pedidos.domain.Pedido;
import com.pypaprint.pedidos.domain.Producto;
import com.pypaprint.pedidos.domain.ProductoUpload;
import com.pypaprint.pedidos.domain.StatusPedido;
import com.pypaprint.pedidos.domain.security.Authority;
import com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment;
import com.pypaprint.pedidos.domain.security.Usuario;
import com.pypaprint.pedidos.services.AuthorityPrincipalAssignmentService;
import com.pypaprint.pedidos.services.AuthorityService;
import com.pypaprint.pedidos.services.ClienteService;
import com.pypaprint.pedidos.services.PedidoService;
import com.pypaprint.pedidos.services.ProductoService;
import com.pypaprint.pedidos.services.ProductoUploadService;
import com.pypaprint.pedidos.services.StatusPedidoService;
import com.pypaprint.pedidos.services.UsuarioService;
import com.pypaprint.pedidos.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    @Autowired
    ClienteService ApplicationConversionServiceFactoryBean.clienteService;
    
    @Autowired
    PedidoService ApplicationConversionServiceFactoryBean.pedidoService;
    
    @Autowired
    ProductoService ApplicationConversionServiceFactoryBean.productoService;
    
    @Autowired
    ProductoUploadService ApplicationConversionServiceFactoryBean.productoUploadService;
    
    @Autowired
    StatusPedidoService ApplicationConversionServiceFactoryBean.statusPedidoService;
    
    @Autowired
    AuthorityService ApplicationConversionServiceFactoryBean.authorityService;
    
    @Autowired
    AuthorityPrincipalAssignmentService ApplicationConversionServiceFactoryBean.authorityPrincipalAssignmentService;
    
    @Autowired
    UsuarioService ApplicationConversionServiceFactoryBean.usuarioService;
    
    public Converter<Cliente, String> ApplicationConversionServiceFactoryBean.getClienteToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.pypaprint.pedidos.domain.Cliente, java.lang.String>() {
            public String convert(Cliente cliente) {
                return new StringBuilder().append(cliente.getNombre()).append(' ').append(cliente.getApellido()).append(' ').append(cliente.getEmail()).append(' ').append(cliente.getTelefono()).toString();
            }
        };
    }
    
    public Converter<Long, Cliente> ApplicationConversionServiceFactoryBean.getIdToClienteConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.pypaprint.pedidos.domain.Cliente>() {
            public com.pypaprint.pedidos.domain.Cliente convert(java.lang.Long id) {
                return clienteService.findCliente(id);
            }
        };
    }
    
    public Converter<String, Cliente> ApplicationConversionServiceFactoryBean.getStringToClienteConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.pypaprint.pedidos.domain.Cliente>() {
            public com.pypaprint.pedidos.domain.Cliente convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Cliente.class);
            }
        };
    }
    
    public Converter<Pedido, String> ApplicationConversionServiceFactoryBean.getPedidoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.pypaprint.pedidos.domain.Pedido, java.lang.String>() {
            public String convert(Pedido pedido) {
                return new StringBuilder().append(pedido.getFechaDeRecepcion()).append(' ').append(pedido.getFechaPropuestaDeEntrega()).append(' ').append(pedido.getAbono()).append(' ').append(pedido.getTotalBs()).toString();
            }
        };
    }
    
    public Converter<Long, Pedido> ApplicationConversionServiceFactoryBean.getIdToPedidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.pypaprint.pedidos.domain.Pedido>() {
            public com.pypaprint.pedidos.domain.Pedido convert(java.lang.Long id) {
                return pedidoService.findPedido(id);
            }
        };
    }
    
    public Converter<String, Pedido> ApplicationConversionServiceFactoryBean.getStringToPedidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.pypaprint.pedidos.domain.Pedido>() {
            public com.pypaprint.pedidos.domain.Pedido convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Pedido.class);
            }
        };
    }
    
    public Converter<Producto, String> ApplicationConversionServiceFactoryBean.getProductoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.pypaprint.pedidos.domain.Producto, java.lang.String>() {
            public String convert(Producto producto) {
                return new StringBuilder().append(producto.getNombre()).append(' ').append(producto.getDescripcion()).append(' ').append(producto.getPrecio()).append(' ').append(producto.getCantidad()).toString();
            }
        };
    }
    
    public Converter<Long, Producto> ApplicationConversionServiceFactoryBean.getIdToProductoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.pypaprint.pedidos.domain.Producto>() {
            public com.pypaprint.pedidos.domain.Producto convert(java.lang.Long id) {
                return productoService.findProducto(id);
            }
        };
    }
    
    public Converter<String, Producto> ApplicationConversionServiceFactoryBean.getStringToProductoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.pypaprint.pedidos.domain.Producto>() {
            public com.pypaprint.pedidos.domain.Producto convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Producto.class);
            }
        };
    }
    
    public Converter<ProductoUpload, String> ApplicationConversionServiceFactoryBean.getProductoUploadToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.pypaprint.pedidos.domain.ProductoUpload, java.lang.String>() {
            public String convert(ProductoUpload productoUpload) {
                return "(no displayable fields)";
            }
        };
    }
    
    public Converter<Long, ProductoUpload> ApplicationConversionServiceFactoryBean.getIdToProductoUploadConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.pypaprint.pedidos.domain.ProductoUpload>() {
            public com.pypaprint.pedidos.domain.ProductoUpload convert(java.lang.Long id) {
                return productoUploadService.findProductoUpload(id);
            }
        };
    }
    
    public Converter<String, ProductoUpload> ApplicationConversionServiceFactoryBean.getStringToProductoUploadConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.pypaprint.pedidos.domain.ProductoUpload>() {
            public com.pypaprint.pedidos.domain.ProductoUpload convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), ProductoUpload.class);
            }
        };
    }
    
    public Converter<StatusPedido, String> ApplicationConversionServiceFactoryBean.getStatusPedidoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.pypaprint.pedidos.domain.StatusPedido, java.lang.String>() {
            public String convert(StatusPedido statusPedido) {
                return new StringBuilder().append(statusPedido.getStatus()).append(' ').append(statusPedido.getStatus_propertie()).toString();
            }
        };
    }
    
    public Converter<Long, StatusPedido> ApplicationConversionServiceFactoryBean.getIdToStatusPedidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.pypaprint.pedidos.domain.StatusPedido>() {
            public com.pypaprint.pedidos.domain.StatusPedido convert(java.lang.Long id) {
                return statusPedidoService.findStatusPedido(id);
            }
        };
    }
    
    public Converter<String, StatusPedido> ApplicationConversionServiceFactoryBean.getStringToStatusPedidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.pypaprint.pedidos.domain.StatusPedido>() {
            public com.pypaprint.pedidos.domain.StatusPedido convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), StatusPedido.class);
            }
        };
    }
    
    public Converter<Authority, String> ApplicationConversionServiceFactoryBean.getAuthorityToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.pypaprint.pedidos.domain.security.Authority, java.lang.String>() {
            public String convert(Authority authority) {
                return new StringBuilder().append(authority.getNombreDeRol()).toString();
            }
        };
    }
    
    public Converter<Long, Authority> ApplicationConversionServiceFactoryBean.getIdToAuthorityConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.pypaprint.pedidos.domain.security.Authority>() {
            public com.pypaprint.pedidos.domain.security.Authority convert(java.lang.Long id) {
                return authorityService.findAuthority(id);
            }
        };
    }
    
    public Converter<String, Authority> ApplicationConversionServiceFactoryBean.getStringToAuthorityConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.pypaprint.pedidos.domain.security.Authority>() {
            public com.pypaprint.pedidos.domain.security.Authority convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Authority.class);
            }
        };
    }
    
    public Converter<AuthorityPrincipalAssignment, String> ApplicationConversionServiceFactoryBean.getAuthorityPrincipalAssignmentToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment, java.lang.String>() {
            public String convert(AuthorityPrincipalAssignment authorityPrincipalAssignment) {
                return "(no displayable fields)";
            }
        };
    }
    
    public Converter<Long, AuthorityPrincipalAssignment> ApplicationConversionServiceFactoryBean.getIdToAuthorityPrincipalAssignmentConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment>() {
            public com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment convert(java.lang.Long id) {
                return authorityPrincipalAssignmentService.findAuthorityPrincipalAssignment(id);
            }
        };
    }
    
    public Converter<String, AuthorityPrincipalAssignment> ApplicationConversionServiceFactoryBean.getStringToAuthorityPrincipalAssignmentConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment>() {
            public com.pypaprint.pedidos.domain.security.AuthorityPrincipalAssignment convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), AuthorityPrincipalAssignment.class);
            }
        };
    }
    
    public Converter<Usuario, String> ApplicationConversionServiceFactoryBean.getUsuarioToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.pypaprint.pedidos.domain.security.Usuario, java.lang.String>() {
            public String convert(Usuario usuario) {
                return new StringBuilder().append(usuario.getPrimerNombre()).append(' ').append(usuario.getSegundoNombre()).append(' ').append(usuario.getPrimerApellido()).append(' ').append(usuario.getSegundoApellido()).toString();
            }
        };
    }
    
    public Converter<Long, Usuario> ApplicationConversionServiceFactoryBean.getIdToUsuarioConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.pypaprint.pedidos.domain.security.Usuario>() {
            public com.pypaprint.pedidos.domain.security.Usuario convert(java.lang.Long id) {
                return usuarioService.findUsuario(id);
            }
        };
    }
    
    public Converter<String, Usuario> ApplicationConversionServiceFactoryBean.getStringToUsuarioConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.pypaprint.pedidos.domain.security.Usuario>() {
            public com.pypaprint.pedidos.domain.security.Usuario convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Usuario.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getClienteToStringConverter());
        registry.addConverter(getIdToClienteConverter());
        registry.addConverter(getStringToClienteConverter());
        registry.addConverter(getPedidoToStringConverter());
        registry.addConverter(getIdToPedidoConverter());
        registry.addConverter(getStringToPedidoConverter());
        registry.addConverter(getProductoToStringConverter());
        registry.addConverter(getIdToProductoConverter());
        registry.addConverter(getStringToProductoConverter());
        registry.addConverter(getProductoUploadToStringConverter());
        registry.addConverter(getIdToProductoUploadConverter());
        registry.addConverter(getStringToProductoUploadConverter());
        registry.addConverter(getStatusPedidoToStringConverter());
        registry.addConverter(getIdToStatusPedidoConverter());
        registry.addConverter(getStringToStatusPedidoConverter());
        registry.addConverter(getAuthorityToStringConverter());
        registry.addConverter(getIdToAuthorityConverter());
        registry.addConverter(getStringToAuthorityConverter());
        registry.addConverter(getAuthorityPrincipalAssignmentToStringConverter());
        registry.addConverter(getIdToAuthorityPrincipalAssignmentConverter());
        registry.addConverter(getStringToAuthorityPrincipalAssignmentConverter());
        registry.addConverter(getUsuarioToStringConverter());
        registry.addConverter(getIdToUsuarioConverter());
        registry.addConverter(getStringToUsuarioConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}