package ua.com.ex.tools.imageloader;

public interface ImageLoader {

    public boolean checkIfProductImageExist(int productId) throws Exception;

    public String getProductImageById(int productId) throws Exception;

    public boolean checkIfCategoryImageExist(int categoryId) throws Exception;

    public String getCategoryImageById(int categoryId) throws Exception;   
    
    public String getDafaultImage() throws Exception;   

}