package ua.com.ex.tools.http;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.ex.model.Product;
import ua.com.ex.service.interfaces.ProductService;
import ua.com.ex.tools.MyTimer;
import ua.com.ex.tools.path.GetPath;

@Component("remoteFileInformation")
public class RemoteFileInformation {

	@Autowired
	private ProductService productService;	

	public void updateByCategoryId(int id) throws Exception{
		List<Product> productAllByParentCategory = productService.getProductByCategoryId(id);
		String path = "";
		MyTimer timer = new MyTimer();
		timer.start();
		for(Product current:productAllByParentCategory){
		    path = GetPath.getRemoteProductImagePath(current.getId());			
			getLastModifiedDate(path);
		}
		timer.stop();
		System.out.println(timer.getResult());
	}

	public Long getLastModifiedDate(String urlAsString) throws Exception{
		URL url = new URL(urlAsString);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		return httpCon.getLastModified();		
	}

}
