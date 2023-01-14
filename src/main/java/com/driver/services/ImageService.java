package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        List<Image> imageList = blog.getImageList();
        Image image = new Image(description,dimensions);
        imageList.add(image);
        blog.setImageList(imageList);
        return image;
    }

    public void deleteImage(Image image){

    }

    public Image findById(int id) {
        return null;

    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        return 0;

    }
}
