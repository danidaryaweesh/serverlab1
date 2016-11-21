package controller;

import DAO.UserDao;
import model.User;
import service.UserService;
import service.UserServiceImpl;

/**
 * Created by Alican on 2016-11-21.
 */
public class UserController {
    private UserService userService;

    public UserController(){
        userService = new UserServiceImpl();
    }//userController

    public User login(User user){
        user = userService.login(user);
        if(user != null)
            return user;
            //return createDao(user);
        else
            return null;
    }//login

    public UserDao register(User user){
        user = userService.register(user);
        if(user != null)
            return createDao(user);
        else
            return null;
    }//register

    private UserDao createDao(User user){
        UserDao userDao = new UserDao();
        userDao.setUsername(user.getUsername());
        userDao.setAge(user.getAge());
        userDao.setAddress(user.getAddress());
        userDao.setWorkTitle(user.getWorkTitle());
        userDao.setId(user.getId());
        return userDao;
    }//create
}//class
