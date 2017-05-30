package ua.com.ex.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.ex.exception.ServiceException;
import ua.com.ex.exception.ToolsException;
import ua.com.ex.model.ConnectionDetail;
import ua.com.ex.model.DownloadFileDetail;
import ua.com.ex.service.interfaces.RemoteDataService;
import ua.com.ex.tools.Arhivator;
import ua.com.ex.tools.FileOperation;
import ua.com.ex.tools.FtpDownloader;
import ua.com.ex.tools.FtpDownloaderImpl;
import ua.com.ex.tools.RemoteCategoryParser;
import ua.com.ex.tools.RemoteProductParser;

@Component(value="remoteDataService")
public class RemoteDataServiceImpl implements RemoteDataService {

    private static final Logger logger = LoggerFactory.getLogger(RemoteDataServiceImpl.class);

    @Autowired   
    private RemoteCategoryParser remoteCategoryParser;

    @Autowired    
    private RemoteProductParser remoteProductParser;

    @Autowired
    private Arhivator arhivator;

    @Autowired
    private FileOperation  fileOperation;

    @Override
    public void updateData() throws ServiceException {
        try {
            clean();  
            downloadFile();
            logger.info("updateData() - start");
            remoteProductParser.updateData();
            remoteCategoryParser.updateData();           
            logger.info("updateData() - done");
        } catch (Exception e) {

        }  
    } 

    private void downloadFile() throws Exception {        
        FtpDownloader ftpDownloader = new FtpDownloaderImpl();
        ftpDownloader.connect(new ConnectionDetail("ex.com.ua", "makeev", "kathmandu12"));
        ftpDownloader.download(new DownloadFileDetail("exbase.sql.gz", "localexbase.sql.gz"));
        ftpDownloader.disconnect();        
        arhivator.unzip("localexbase.sql.gz", "localexbase.sql");
        logger.info("downloadFile() - done");        
    }

    private void clean() throws ToolsException {
        fileOperation.cleanOldFile("localexbase.sql");
        fileOperation.cleanOldFile("localexbase.sql.gz");
    }

}
