package service;

import model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Alican on 2016-11-21.
 */
public class UserServiceImpl implements UserService{
    private EntityManager em;

    public UserServiceImpl(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
    }//UserServiceImpl

    @Override
    public User login(User user){
        User qUser = em.find(user.getClass(), user.getUsername());
        if(qUser != null){
            //User exists, check if password matches
            if(user.getPassword().equals(qUser.getPassword())){
                //Password is correct, login successful
                return qUser;
            }//if
            else{
                //Password is wrong, login failed
                return null;
            }//else
        }//if
        else{
            //user does not exist, login failed
            return null;
        }//else
    }//login

    @Override
    public User register(User user) {
        User qUser = em.find(user.getClass(), user.getUsername());
        if(qUser == null){
            //User does not exist, register user
            //create lists of message and log
            return qUser;
        }//if
        else{
            //user exist, register failed
            return null;
        }//else
    }//register
}//class
