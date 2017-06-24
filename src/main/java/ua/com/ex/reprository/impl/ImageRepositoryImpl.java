package ua.com.ex.reprository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.tools.file.FileOperation;
import ua.com.ex.tools.http.RemoteFileInformation;
import ua.com.ex.tools.imageloader.ImageLoader;
import ua.com.ex.tools.path.GetPath;

public abstract class ImageRepositoryImpl implements ImageRepository{

    @Autowired
    @Qualifier("imageLocalLoader")
    private ImageLoader imageLocalLoader;

    @Autowired
    @Qualifier("imageRemoteLoader")
    private ImageLoader imageRemoteLoader;

    @Autowired
    private FileOperation fileOperation;

    @Autowired
    private RemoteFileInformation remoteFileInformation;
    
    
    protected abstract String getLocalPath(int productId) ;
   
    protected abstract String getRemotePath(int id);
    
   
	protected String getRemotePathForColor(int id) {
		return GetPath.getRemoteProductImagePathForColor(id);
	}

    @Override
    public String getById(int id) throws Exception {
        String path = getLocalPath(id);
        String result = "";
        if(imageLocalLoader.checkIfExist(path)){
        	System.out.println("ImageRepositoryImpl.getById() exist "+path);
            result = imageLocalLoader.getImage(path); 
        }else{
        	System.out.println("ImageRepositoryImpl.getById() not exist "+path);
        }
        
        return result;
    }
   
  

    @Override
    public List<String> getByList(List<Integer> list) throws Exception {
        List<String> result = new ArrayList<>();
        for(Integer current : list){
            result.add(getById(current));
        }
        return result;
    }    

    @Override
    public void save(int id, String image) throws Exception {        
        fileOperation.save(getLocalPath(id), image);
    }    

    @Override
    public void update(int id) throws Exception {
        Long dateLocal = getLastModifiedDate(id);
        if(dateLocal == 0){      
            String path = getRemotePath(id);
            String image = imageRemoteLoader.getImage(path);
            if(!image.isEmpty()){
                save(id, image);
            }
            else{
            	path = getRemotePathForColor(id);
                image = imageRemoteLoader.getImage(path);
                if(!image.isEmpty()){
                    save(id, image);
                }
            }
        } else {    
            String pathRemote = getRemotePath(id);
            Long dateRemote = remoteFileInformation.getLastModifiedDate(pathRemote);
            if (dateRemote > dateLocal){ 
                String pathLocal = getLocalPath(id);
                String image = imageRemoteLoader.getImage(pathRemote);
                fileOperation.cleanOldFile(pathLocal);
                save(id, image);  
            }
        }
    }    

    private Long getLastModifiedDate(int id) {
        String path = getLocalPath(id);
        if(fileOperation.isExist(path)){
            return fileOperation.getLastModifiedDate(path);
        }
        return new Long(0);
    }   

    @Override
    public String getDefault() throws Exception {        
        return imageLocalLoader.getDafaultImage();
    }
    
}
