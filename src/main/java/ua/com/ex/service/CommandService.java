package ua.com.ex.service;

public interface CommandService {

    public void shutdown();  
    
    public void updateImageCatalogProductItem() throws Exception;
    
    public void updateImageCatalogCategoryItem() throws Exception;
    
    public void updateData() throws Exception;   
    
}
