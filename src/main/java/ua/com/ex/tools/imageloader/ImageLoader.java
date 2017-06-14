package ua.com.ex.tools.imageloader;

public interface ImageLoader {

    public boolean checkIfExist(String path) throws Exception;

    public String getImage(String path) throws Exception; 
    
    public String getDafaultImage() throws Exception;   

}