package ua.com.ex.configuration;

import lombok.Getter;

@Getter
public class DownloadFileDetail {
    private final String remoteFilePath = "exbase.sql.gz";  
    private final String localFileName = "localexbase.sql.gz";
}
