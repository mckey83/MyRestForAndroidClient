package ua.com.ex.configuration;

public class Path {

    private static final String REMOTE = "https://ex.com.ua/media/images/products_images/290x385/";
    
    private static final String LOCAL = "/home/alex/Public/products_images_26-06-2017/products_images/";

    private static Path path = new Path( );

    private Path() { }

    public static Path getInstance( ) {
        return path;
    }

    public static String getRemoteProductImagePath(int id){
        return REMOTE +id+"_thumb.png";
    }

    public static String getRemoteProductImagePathForColor(int id){
        return REMOTE +id+"_color_enl.png";
    }

    public static String getRemoteCategoryImagePath(int id){
        return "https://ex.com.ua/media/images/categories/cat_"+id+".png"; 
    }

    public static String getLocalProductImagePath(int id){
        return LOCAL+ id + "_enl.png";
    }

    public static String getLocalCategoryImagePath(int id){
        return "/home/alex/workspaceEE/rest/images/categories/cat_"+ id + ".png";
    }

    public static String getLocalDefaultImagePath(){
        return "/home/alex/workspaceEE/rest/images/ex.png";
    }
}
