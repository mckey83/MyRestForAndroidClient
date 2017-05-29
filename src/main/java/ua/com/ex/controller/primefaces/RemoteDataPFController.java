package ua.com.ex.controller.primefaces;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ua.com.ex.exception.ToolsException;
import ua.com.ex.model.ConnectionDetail;
import ua.com.ex.model.DownloadFileDetail;
import ua.com.ex.reprository.interfaces.CategoryRepository;
import ua.com.ex.reprository.interfaces.ProductRepository;
import ua.com.ex.service.interfaces.RemoteDataService;
import ua.com.ex.tools.Arhivator;
import ua.com.ex.tools.FileOperation;
import ua.com.ex.tools.FtpDownloader;
import ua.com.ex.tools.FtpDownloaderImpl;

@Getter
@Setter
@Controller
@ViewScoped
@ManagedBean
public class RemoteDataPFController implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(RemoteDataPFController.class);

    @Autowired
    @Qualifier("remoteDataServiceCategory")
    private RemoteDataService remoteDataServiceCategory;

    @Autowired
    @Qualifier("remoteDataServiceProduct")
    private RemoteDataService remoteDataServiceProduct;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Arhivator arhivator;

    @Autowired
    private FileOperation  fileOperation;

    private static final long serialVersionUID = 1L;

    private String  databaseDetails;    


    @PostConstruct
    public void init() {
        databaseDetails = " categories : "+categoryRepository.findAll().size() + " | " +
                " products : "+productRepository.findAll().size();

    }

    public void update() {  
        long start = System.currentTimeMillis();             
        try {
            clean();  
            downloadFile(); 
            remoteDataServiceCategory.updateData();
            remoteDataServiceProduct.updateData();
            databaseDetails = " categories : "+categoryRepository.findAll().size() +
                    " products : "+productRepository.findAll().size();
        } catch (Exception e) {
            databaseDetails = "error load resource";
        }         
        long elapsedTimeMillis = System.currentTimeMillis()-start;        
        databaseDetails += " time "+elapsedTimeMillis/1000F+ " s";
    }

    private void clean() throws ToolsException {
        fileOperation.cleanOldFile("localexbase.sql");
        fileOperation.cleanOldFile("localexbase.sql.gz");
    }


    private void downloadFile() throws Exception {      
              
        FtpDownloader ftpDownloader = new FtpDownloaderImpl();
        ftpDownloader.connect(new ConnectionDetail("ex.com.ua", "makeev", "kathmandu12"));
        ftpDownloader.download(new DownloadFileDetail("exbase.sql.gz", "localexbase.sql.gz"));
        ftpDownloader.disconnect();        
        arhivator.unzip("localexbase.sql.gz", "localexbase.sql");
        logger.info("downloadFile() - done");        
    }
}
