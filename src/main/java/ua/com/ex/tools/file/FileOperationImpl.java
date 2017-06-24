package ua.com.ex.tools.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.primefaces.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.com.ex.exception.ToolsException;


@Component("fileOperation")
public class FileOperationImpl implements FileOperation {

	private static final Logger logger = LoggerFactory.getLogger(FileOperationImpl.class);

	@Override
	public void cleanOldFile(String name) {         
		File file = new File(name);        
		if (file.exists()) {
			file.delete();
		} 
	}



	@Override
	public  String readTextFile(String path) throws ToolsException {            
		ByteArrayOutputStream result = null;
		String resultAsString = "";
		try { 
			InputStream inputStream = getInputStream(path);             
			result = readStream(inputStream);
			resultAsString = new String (result.toByteArray());
			result.close();
		} catch (Exception e) {                    
			logger.error("readExternFile() "+path+" ",e);
			throw new ToolsException("readExternFile() "+path+"Exception");
		}        
		return resultAsString;
	}

	private ByteArrayOutputStream readStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream result;
		result = new ByteArrayOutputStream();      
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}   
		inputStream.close();
		return result;
	}

	private InputStream getInputStream(String path) throws FileNotFoundException {
		InputStream inputStream;
		if (Files.exists(Paths.get(path))) {      
			inputStream = new FileInputStream(path);            
		} else {
			String none ="";
			inputStream = new ByteArrayInputStream(none.getBytes(StandardCharsets.UTF_8));
		}      
		return inputStream;
	}

	@Override
	public byte[] readBinaryFile(String path) throws ToolsException {
		try {
			return Files.readAllBytes(new File(path).toPath());
		} catch (IOException e) {
			e.printStackTrace();
			byte[] result = {0};
			return result;
		} 
	} 

	@Override
	public void save(String path, String image) throws ToolsException {        
		try {
			PrintStream out = new PrintStream(new FileOutputStream(new File(path)));
			out.write(Base64.decode(image));
			out.close();
		} catch (IOException e) {  
			logger.error("save() "+path+" ",e.getMessage());
			throw new ToolsException("save() "+path+" " + e.getMessage()); 
		}   
	}

	@Override
	public boolean isExist(String path) {       
		return Files.exists(Paths.get(path));
	}



	@Override
	public Long getLastModifiedDate(String path) {
		File file = new File(path); 
		return file.lastModified(); 
	}



	@Override
	public void setLastModifiedDate(String path, Long date) {
		File file = new File(path);
		file.setLastModified(date);        
	}

}
