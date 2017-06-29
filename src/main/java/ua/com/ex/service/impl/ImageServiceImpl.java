package ua.com.ex.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.com.ex.exception.ServiceException;
import ua.com.ex.reprository.ImageRepository;
import ua.com.ex.service.ImageService;

@Service("imageService")
public class ImageServiceImpl implements ImageService {


    @Autowired 
    @Qualifier("imageProductRepository")
    private ImageRepository imageProductRepository;

    @Override
    public boolean save(int id, String image) throws Exception  {
        boolean result = false;
        try {
            result = imageProductRepository.save(id, image);
        } catch (Exception e) {
            throw new ServiceException(e);
        }   
        return result;
    }



}
