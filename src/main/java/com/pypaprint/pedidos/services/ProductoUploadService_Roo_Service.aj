// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.pypaprint.pedidos.services;

import com.pypaprint.pedidos.domain.ProductoUpload;
import com.pypaprint.pedidos.services.ProductoUploadService;
import java.util.List;

privileged aspect ProductoUploadService_Roo_Service {
    
    public abstract long ProductoUploadService.countAllProductoUploads();    
    public abstract void ProductoUploadService.deleteProductoUpload(ProductoUpload productoUpload);    
    public abstract ProductoUpload ProductoUploadService.findProductoUpload(Long id);    
    public abstract List<ProductoUpload> ProductoUploadService.findAllProductoUploads();    
    public abstract List<ProductoUpload> ProductoUploadService.findProductoUploadEntries(int firstResult, int maxResults);    
    public abstract void ProductoUploadService.saveProductoUpload(ProductoUpload productoUpload);    
    public abstract ProductoUpload ProductoUploadService.updateProductoUpload(ProductoUpload productoUpload);    
}
