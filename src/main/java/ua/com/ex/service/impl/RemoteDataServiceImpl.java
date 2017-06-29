package ua.com.ex.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.ex.configuration.ConnectionDetail;
import ua.com.ex.configuration.DownloadFileDetail;
import ua.com.ex.exception.ServiceException;
import ua.com.ex.exception.ToolsException;
import ua.com.ex.service.RemoteDataService;
import ua.com.ex.tools.file.Arhivator;
import ua.com.ex.tools.file.FileOperation;
import ua.com.ex.tools.ftp.FtpDownloader;
import ua.com.ex.tools.ftp.FtpDownloaderImpl;
import ua.com.ex.tools.parser.CategoryParser;
import ua.com.ex.tools.parser.ProductParser;

@Component(value = "remoteDataService")
public class RemoteDataServiceImpl implements RemoteDataService {

    private static final Logger logger = LoggerFactory.getLogger(RemoteDataServiceImpl.class);

    @Autowired
    private CategoryParser categoryParser;

    @Autowired
    private ProductParser productParser;

    @Autowired
    private Arhivator arhivator;

    @Autowired
    private FileOperation fileOperation;
    
    private ConnectionDetail connectionDetail = new ConnectionDetail();
    
    private DownloadFileDetail downloadFileDetail = new DownloadFileDetail();
    

    @Override
    public void updateData() throws ServiceException {
        try {
            clean();
            downloadFile();
            logger.info("updateData() - start");
            productParser.updateData();
            categoryParser.updateData();
            logger.info("updateData() - done");
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private void downloadFile() throws Exception {
        FtpDownloader ftpDownloader = new FtpDownloaderImpl();
        ftpDownloader.connect(connectionDetail);
        ftpDownloader.download(downloadFileDetail);
        ftpDownloader.disconnect();
        arhivator.unzip("localexbase.sql.gz", "localexbase.sql");
        logger.info("downloadFile() - done");
    }

    private void clean() throws ToolsException {
        fileOperation.cleanOldFile("localexbase.sql");
        fileOperation.cleanOldFile("localexbase.sql.gz");
    }

}
