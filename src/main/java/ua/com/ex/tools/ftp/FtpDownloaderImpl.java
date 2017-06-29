package ua.com.ex.tools.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.com.ex.configuration.ConnectionDetail;
import ua.com.ex.configuration.DownloadFileDetail;
import ua.com.ex.exception.ToolsException;

@Component("ftpDownloaderService")
public class FtpDownloaderImpl implements FtpDownloader {

    private static final Logger logger = LoggerFactory.getLogger(FtpDownloaderImpl.class);
    
    private FTPClient ftp = new FTPClient();

    @Override
    public void connect(ConnectionDetail connectionDetail) throws ToolsException {
        try {            
            
            int reply;
            ftp.connect(connectionDetail.getHost());
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                throw new Exception("Exception in connecting to FTP Server");                
            }
            ftp.login(connectionDetail.getUser(), connectionDetail.getPassword());
            ftp.setFileType(FTP.ASCII_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            logger.info("connect - done");
        } catch (Exception e) {
            logger.error("connect() ", e);
            throw new ToolsException("connect() ", e);
        } 
    }

    @Override
    public void download(DownloadFileDetail downloadFileDetail) throws ToolsException {
        try (FileOutputStream fos = new FileOutputStream(new File(downloadFileDetail.getLocalFileName()),true)) {
            this.ftp.retrieveFile(downloadFileDetail.getRemoteFilePath(), fos);
            logger.info("download - done");
        } catch (IOException e) {           
            logger.error("download() ", e);
            throw new ToolsException("download() ", e);
        }
    }

    @Override
    public void disconnect() throws ToolsException {
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
                logger.info("disconnect - done");
            } catch (IOException f) {
                logger.error("disconnect() ", f);
                throw new ToolsException("disconnect() ", f);
            }
        }
    }  
}
