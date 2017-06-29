package ua.com.ex.controller.rest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    
    @Autowired 
    @Qualifier("imageService")
    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<?> add(@PathVariable("id") int id, @RequestBody String image) {             
        try {
            imageService.save(id, image);
            return new ResponseEntity<>(OK);
        } catch (Exception e) { 
            String message = e.getMessage();
            logger.warn(message);
            return new ResponseEntity<>(message, BAD_REQUEST); 
        }
    }   
}
