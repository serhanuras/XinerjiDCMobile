package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class OrderDocument implements Serializable {

    private long Id ;

    private long OrderId ;

    private long DocumentTypeId ;

    private String FileName ;

    private String FileExtension ;

    private String FileBinary ;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public long getDocumentTypeId() {
        return DocumentTypeId;
    }

    public void setDocumentTypeId(long documentTypeId) {
        DocumentTypeId = documentTypeId;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileExtension() {
        return FileExtension;
    }

    public void setFileExtension(String fileExtension) {
        FileExtension = fileExtension;
    }

    public String getFileBinary() {
        return FileBinary;
    }

    public void setFileBinary(String fileBinary) {
        FileBinary = fileBinary;
    }
}
