import Connection.ConnectToWebservice;
import DAO.UserAuthentication;
import DAO.UserDao;
import com.google.gson.Gson;
import controller.LogController;
import controller.UserController;
import model.Log;
import model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dani on 2016-11-18.
 * whoohhhhh
 */
@ManagedBean(name="userbean", eager = true)
@SessionScoped
public class UserBean {

    private UserController userController = new UserController();
    private ConnectToWebservice connectToWebservice = new ConnectToWebservice();
    private String username;
    private String password;
    private int age;
    private String address;
    private String workTitle;
    private UserDao userDao;
    private User user;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage(){
        return "hello";
    }

    public String doLogin(){
        Gson gson = new Gson();
        UserAuthentication tmp = new UserAuthentication();
        tmp.setUsername(username);
        tmp.setPassword(password);

        String response = connectToWebservice.loginRequest(gson.toJson(tmp));
        if(response.contains("empty")){
            System.out.println("Failed to log in!");

            return"login.xhtml";
        }else{
            userDao = gson.fromJson(response, UserDao.class);
            setAge(userDao.getAge());
            setAddress(userDao.getAddress());
            setWorkTitle(userDao.getWorkTitle());
            return "index.xhtml";
        }
    }

    public void doRegister(){
        Gson gson = new Gson();

        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setUsername(username);
        userAuthentication.setPassword(password);
        userAuthentication.setAddress(address);
        userAuthentication.setAge(age);
        userAuthentication.setWorkTitle(workTitle);

        String json = gson.toJson(userAuthentication);
        String response = connectToWebservice.addUser(json);

        if(response.contains("empty")){
            System.out.println("Failed to register!");
        }else{
            userDao = gson.fromJson(response, UserDao.class);
            System.out.println("Saved user: "+userDao.getUsername());
        }
    }

    public String backToLogin(){
        return "login.xhtml";
    }

    public String backToRegister(){
        return "register.xhtml";
    }



    private LogController logController = new LogController();
    private String title;
    private String content;
    private Calendar date = Calendar.getInstance();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void addLog(){
        if(title != null && content != null){
            Log log = new Log();
            log.setOwner(user);
            System.out.println("the index username is: " + user.getUsername());
            log.setTitle(title);
            log.setContent(content);
            log.setDate(Calendar.getInstance());
            logController.addLog(log);
        }//if
    }//addLog

    public String getLogs(){
        List<Log> logList = user.getLog();
        return logList.toString();
    }
}//class
