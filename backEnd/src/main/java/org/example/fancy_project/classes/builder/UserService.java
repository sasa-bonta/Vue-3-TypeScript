package org.example.fancy_project.classes.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public User create(User user){
        return userDao.save(user);
    }

    public List<User> getAll() {
        return userDao.findAll();
    }
}
