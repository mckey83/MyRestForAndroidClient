package ua.com.ex.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DownloadFileDetail {
    private String remoteFilePath; 
    private String localFileName;
}
