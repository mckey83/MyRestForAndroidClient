package ua.com.ex.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;



public class FtpDownloader {

        private FTPClient ftp = null;

        public FtpDownloader(String host, String user, String pwd) throws Exception {
            ftp = new FTPClient();
            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            int reply;
            ftp.connect(host);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                throw new Exception("Exception in connecting to FTP Server");
            }
            ftp.login(user, pwd);
            ftp.setFileType(FTP.ASCII_FILE_TYPE);
            ftp.enterLocalPassiveMode();
        }

        public void downloadFile(String remoteFilePath, String localFilePath) {
            try (FileOutputStream fos = new FileOutputStream(localFilePath)) {
                this.ftp.retrieveFile(remoteFilePath, fos);
            } catch (IOException e) {
                System.out.println("downloadFile error:");
                e.printStackTrace();
            }
        }

        public void disconnect() {
            if (this.ftp.isConnected()) {
                try {
                    this.ftp.logout();
                    this.ftp.disconnect();
                } catch (IOException f) {
                    f.printStackTrace();
                }
            }
        }
    
}
