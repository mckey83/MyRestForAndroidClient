package ua.com.ex.tools.ftp;

import ua.com.ex.configuration.ConnectionDetail;
import ua.com.ex.configuration.DownloadFileDetail;
import ua.com.ex.exception.ToolsException;

public interface FtpDownloader {

    public void connect(ConnectionDetail connectionDetail) throws ToolsException;
   
    public void download(DownloadFileDetail downloadFileDetail) throws ToolsException;
   
    public void disconnect() throws ToolsException;   

}
