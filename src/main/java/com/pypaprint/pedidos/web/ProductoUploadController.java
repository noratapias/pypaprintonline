package com.pypaprint.pedidos.web;
import com.pypaprint.pedidos.domain.ProductoUpload;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productouploads")
@Controller
@RooWebScaffold(path = "productouploads", formBackingObject = ProductoUpload.class)
public class ProductoUploadController {
}
