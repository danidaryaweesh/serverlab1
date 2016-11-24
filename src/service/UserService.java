package service;

import model.User;

import javax.xml.ws.ServiceMode;

/**
 * Created by Alican on 2016-11-21.
 */
@ServiceMode
public interface UserService {
    public User login(User user);
    public User register(User user);
}
