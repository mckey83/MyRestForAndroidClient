package ua.com.ex.reprository;

import java.util.List;


public interface ImageRepository {
    
    String getDefault() throws Exception;
    
	String getById(int id) throws Exception;
	
	List<String> getByList(List<Integer> list) throws Exception;	
	
	boolean save(int id, String image) throws Exception;	
	
	void update(int id) throws Exception;
	
}
