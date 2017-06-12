package ua.com.ex.tools.path;

public class GetPath {
    
    private static GetPath getPath = new GetPath( );
    
    private GetPath() { }
    
    public static GetPath getInstance( ) {
        return getPath;
     }
    
   public static String getRemoteProductImagePath(int id){
        return "https://ex.com.ua/media/images/products_images/"+id+"_thumb.png";
    }
    
    public static String getRemoteCategoryImagePath(int id){
        return "https://ex.com.ua/media/images/categories/cat_"+id+".png"; 
    }
    
    public static String getLocalProductImagePath(int id){
        return "/images/products/"+ id + "_thumb.png";
    }
    
    public static String getLocatCategoryImagePath(int id){
        return "/images/categories/cat_"+ id + ".png";
    }
}
