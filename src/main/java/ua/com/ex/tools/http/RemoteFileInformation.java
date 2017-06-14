package ua.com.ex.tools.http;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component("remoteFileInformation")
public class RemoteFileInformation {

	public Long getLastModifiedDate(String urlAsString) throws Exception{
		URL url = new URL(urlAsString);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		return httpCon.getLastModified();		
	}

}
