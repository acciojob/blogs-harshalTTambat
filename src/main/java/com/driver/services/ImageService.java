package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;
    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        List<Image> imageList = blog.getImageList();
        Image image = new Image(description,dimensions);
        imageList.add(image);
        blog.setImageList(imageList);
        imageRepository2.save(image);
        return image;
    }

    public void deleteImage(int imageId){

        Image image = imageRepository2.findById(imageId).get();
        Blog blog = image.getBlog();
        List<Image> imageList = blog.getImageList();
        imageList.remove(image);
        blog.setImageList(imageList);
        imageRepository2.delete(image);
        blogRepository.save(blog);
    }

    public Image findById(int id) {
        Image image = imageRepository2.findById(id).get();
        return image;
    }

    public int countImagesInScreen(int imageId, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        Image image = imageRepository2.findById(imageId).get();
        if(screenDimensions.split("x").length==2 || Objects.nonNull(image)){
            int maxlength=Integer.parseInt(screenDimensions.split("x")[0])/Integer.parseInt(image.getDimensions().split("x")[0]);
            int maxwidth=Integer.parseInt(screenDimensions.split("x")[1])/Integer.parseInt(image.getDimensions().split("x")[1]);
            return maxlength*maxwidth;
        }
        return 0;

    }
}
