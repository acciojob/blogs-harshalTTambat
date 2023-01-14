package com.driver.services;

import com.driver.RequestDTO.UserRequestDto;
import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogService blogService3;

    public void createUser(User user){

        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setLastName(user.getLastName());
        user1.setFirstName(user.getFirstName());

        Blog blog = blogService3.createBlog(user1);
        List<Blog> blogs = user1.getBlogList();
        blogs.add(blog);
        user1.setBlogList(blogs);


        userRepository3.save(user1);
    }
    public void deleteUser(int userId){

        User user = userRepository3.findById(userId).get();
        userRepository3.delete(user);
        // blocks related to user will automatically delete because of bidirectional relation
    }

    public void updateUser(User user){

        User userToBeUpdated = userRepository3.findById(user.getId()).get();

        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setUsername(user.getUsername());
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setBlogList(user.getBlogList());

        userRepository3.save(userToBeUpdated);
    }

    public User findUserByUsername(String username){

        List<User> users = userRepository3.findAll();
        for (User user: users)
        {
            String userN = user.getUsername();
            if(userN.equals(username))
            {
                return user;
            }
        }
        return null;
    }
}
