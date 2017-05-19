package ua.com.ex.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.springframework.stereotype.Component;


@Component(value="remoteDataService")
public class RemoteDataServiceImpl implements RemoteDataService {

    private FtpDownloader ftpDownloader;

    @Override
    public String  updateFromEx() {
        return downloadFile();        
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


}


