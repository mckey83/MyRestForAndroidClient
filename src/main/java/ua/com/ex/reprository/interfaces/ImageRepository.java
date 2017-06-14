package ua.com.ex.reprository.interfaces;

import java.util.List;


public interface ImageRepository {
    
    String getDefault() throws Exception;
    
	String getById(int id) throws Exception;
	
	List<String> getByList(List<Integer> list) throws Exception;	
	
	void save(int id, String image) throws Exception;	
	
	void update(int id) throws Exception;
	
}
