package ua.com.ex.tools;

import ua.com.ex.exception.ToolsException;
import ua.com.ex.model.ConnectionDetail;
import ua.com.ex.model.DownloadFileDetail;

public interface FtpDownloader {

    public void connect(ConnectionDetail connectionDetail) throws ToolsException;
   
    public void download(DownloadFileDetail downloadFileDetail) throws ToolsException;
   
    public void disconnect() throws ToolsException;   

}
