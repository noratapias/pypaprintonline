package com.pypaprint.pedidos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.pypaprint.pedidos.domain.Producto;
import com.pypaprint.pedidos.domain.ProductoUpload;
import com.pypaprint.pedidos.services.ProductoService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RooWebJson(jsonObject = Producto.class)
@Controller
@RequestMapping("/productoes")
@RooWebScaffold(path = "productoes", formBackingObject = Producto.class)
@RooWebFinder
public class ProductoController {

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    @Autowired
    ProductoService productoService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html")
    public String upload(@Valid ProductoUpload mediaUpload, BindingResult bindingResult, Model uiModel, @RequestParam(value = "file", required = true) MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws IOException {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("mediaUpload", mediaUpload);
            return "productoes/upload";
        }
        InputStreamReader is = new InputStreamReader(multipartFile.getInputStream());
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(is);
        while (br.readLine() != null) {
            String line = br.readLine();
            String[] vecLine = line.split("\t");
            String codigo = vecLine[0].trim();
            if (codigo.startsWith("W")) {
	            String nombre = vecLine[1].trim();
	            String precio = "";
	            String cantidad = "";
	            if (nombre.isEmpty()) {
	            	nombre = vecLine[2].trim();
	            	precio = vecLine[3].trim().replace(".", "").replace(',', '.');
		            cantidad = vecLine[4].trim().replace(".", "").replace(',', '.');
	            } else {
		            precio = vecLine[2].trim().replace(".", "").replace(',', '.');
		            cantidad = vecLine[3].trim().replace(".", "").replace(',', '.');
	            }
	            Long numCod = Producto.countFindProductoesByCodigoEquals(codigo);
	            if (numCod == 0) {
	                Producto newProducto = new Producto();
	                if (cantidad.equalsIgnoreCase("0.00")) {
	                    newProducto.setCantidad(null);
	                } else {
	                	newProducto.setCantidad(Long.parseLong(cantidad));
	                }
	                newProducto.setCodigo(codigo);
	                newProducto.setDescripcion(nombre);
	                newProducto.setDuracionEnProduccion(3.0);
	                newProducto.setNombre(nombre);
	                newProducto.setPrecio(Double.parseDouble(precio));
	                productoService.saveProducto(newProducto);
	            }
            }
        }
        return "redirect:/productoes";
    }

    @RequestMapping(value = "/upload", params = "form", produces = "text/html")
    public String uploadForm(Model uiModel) {
        return "productoes/upload";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Producto producto, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, producto);
            return "productoes/create";
        }
        uiModel.asMap().clear();
        productoService.saveProducto(producto);
        return "redirect:/productoes/" + encodeUrlPathSegment(producto.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Producto());
        return "productoes/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("producto", productoService.findProducto(id));
        uiModel.addAttribute("itemId", id);
        return "productoes/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("productoes", Producto.findProductoEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) productoService.countAllProductoes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("productoes", Producto.findAllProductoes(sortFieldName, sortOrder));
        }
        return "productoes/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Producto producto, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, producto);
            return "productoes/update";
        }
        uiModel.asMap().clear();
        productoService.updateProducto(producto);
        return "redirect:/productoes/" + encodeUrlPathSegment(producto.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Producto producto = productoService.findProducto(id);
        productoService.deleteProducto(producto);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/productoes";
    }

    void populateEditForm(Model uiModel, Producto producto) {
        uiModel.addAttribute("producto", producto);
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
