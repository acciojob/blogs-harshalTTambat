package com.driver.services;

import com.driver.RequestDTO.BlogRequestDto;
import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
        List<Blog> blogList = blogRepository1.findAll();
        return blogList;
    }

    public Blog createBlog(User user)
    {
        Blog blog = new Blog();
        blog.setUser(user);
        return blog;
    }
    public void createAndReturnBlog(Integer userId, String title, String content) {

        //create a blog at the current time

        // finding user by userId
        User user = userRepository1.findById(userId).get();
        // creating blog
        Blog blog = new Blog(title,content);
        // adding blog in user blogList
        List<Blog> blogList = blogRepository1.findById(userId).get().getUser().getBlogList();
        blogList.add(blog);
        //set user blog
        user.setBlogList(blogList);

        //updating the blog details
        //Updating the userInformation and changing its blogs
        blogRepository1.save(blog);
    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        // find blog by blogId
        Blog blog = blogRepository1.findById(blogId).get();
        // creating image & adding image in blog
        Image image = imageService1.createAndReturn(blog, description, dimensions);
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        //finding blog & user by blogId
        Blog blog = blogRepository1.findById(blogId).get();
        User user = blogRepository1.findById(blogId).get().getUser();
        int userId = user.getId();
        List<Blog> blogList = userRepository1.findById(userId).get().getBlogList();
        blogList.remove(blog);
        user.setBlogList(blogList);

        blogRepository1.delete(blog);

    }
}
