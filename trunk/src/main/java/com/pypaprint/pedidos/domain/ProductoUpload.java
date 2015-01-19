package com.pypaprint.pedidos.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ProductoUpload {

    /**
     */
    @NotNull
    @RooUploadedFile(contentType = "application/vnd.ms-excel", autoUpload = true)
    @Lob
    private byte[] file;
}
