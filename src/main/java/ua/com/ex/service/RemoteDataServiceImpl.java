package ua.com.ex.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.CategoryFileRepository;
import ua.com.ex.reprository.CategoryRepository;
import ua.com.ex.reprository.ProductFileRepository;
import ua.com.ex.reprository.ProductRepository;


@Component(value="remoteDataService")
public class RemoteDataServiceImpl implements RemoteDataService {

    private FtpDownloader ftpDownloader;
    @Autowired
    private CategoryFileRepository categoryFileRepository;

    @Autowired
    private ProductFileRepository productFileRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String  updateFromEx() {
        String result = "";
        result += downloadFile(); 
        result += importData();
        return result;      
    }


    private String downloadFile() {
        try {
            ftpDownloader =
                    new FtpDownloader("ex.com.ua", "makeev", "kathmandu12");
            ftpDownloader.downloadFile("exbase.sql.gz", "localexbase.sql.gz");
            System.out.println("FTP File downloaded successfully");
            ftpDownloader.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return gunzipIt();
    }

    public String gunzipIt(){       
        byte[] buffer = new byte[1024];

        try{
            GZIPInputStream gzis = new GZIPInputStream(new FileInputStream("localexbase.sql.gz"));
            FileOutputStream out = new FileOutputStream("localexbase.sql");
            int len;
            while ((len = gzis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            gzis.close();
            out.close();
            return "OK";
        } catch(IOException ex){            
            return ex.toString();
        }
    }


    public String importData() {         
        List<Category> categories = categoryFileRepository.getAll();
        List<Product> products = productFileRepository.getAll();
        if (!categories.isEmpty() && !products.isEmpty()){
            String result = "";
            for(Product current: products){
                productRepository.save(current);
                result+=current.getId()+" "+current.getName()+" "+current.getPrice()+" "+current.getQuantity()+" "+current.getCategoryId()+"<br/>";                        
            }  
            for(Category current: categories){
                int productQuantity = categoryRepository.findProductQuantityByCategoryId(current.getId());
                current.setProductQuantity(productQuantity);
                categoryRepository.save(current);
            } 

            return "OK imported categories: "+categories.size() +" products : "+ products.size()+"<br/>"+result;
        }
        else{
            return "ERROR";  
        }
    }


}


