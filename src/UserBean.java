import Connection.ConnectToWebservice;
import DAO.LogDao;
import DAO.MessageDao;
import DAO.UserAuthentication;
import DAO.UserDao;
import com.google.gson.Gson;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Calendar;

/**
 * Created by dani on 2016-11-18.
 * whoohhhhh
 */
@ManagedBean(name="userbean", eager = true)
@SessionScoped
public class UserBean {

    private ConnectToWebservice connectToWebservice = new ConnectToWebservice();
    private String username;
    private String password;
    private int age;
    private String address;
    private String workTitle;
    private UserDao userDao;

    private LogDao logDao;
    private String title;
    private String content;

    private MessageDao messageDao;
    private String reciever;
    private String messageContent;

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

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getReciever() { return reciever; }

    public void setReciever(String reciever) { this.reciever = reciever; }

    public String getMessageContent() { return messageContent; }

    public void setMessageContent(String messageContent) { this.messageContent = messageContent; }

    public String doLogin(){
        Gson gson = new Gson();
        UserAuthentication tmp = new UserAuthentication();
        tmp.setUsername(username);
        tmp.setPassword(password);

        String response = connectToWebservice.loginRequest(gson.toJson(tmp));
        if(response.contains("Empty")){
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

        if(response.contains("Empty")){
            System.out.println("Failed to register!");
        }else{
            System.out.println("Saved user: "+response);
        }
    }

    public String backToLogin(){
        return "login.xhtml";
    }

    public String backToRegister(){
        return "register.xhtml";
    }

    public void addLog(){
        if(title.length() > 0 && content.length() > 0){
            logDao = new LogDao();
            logDao.setOwner(userDao);
            logDao.setContent(content);
            logDao.setTitle(title);
            logDao.setDate(Calendar.getInstance());

            Gson gson = new Gson();
            String logGson = gson.toJson(logDao, LogDao.class);
            String response = connectToWebservice.addLog(logGson);
            if(response.contains("Empty")){
                System.out.println("Failed to add the log!");
            }else{
                content="";
                title="";
                System.out.println("Added the log");
            }
        }//if
    }//addLog

    public void addMessage(){
        if(reciever.length() > 0 && content.length() > 0){
            messageDao = new MessageDao();
            messageDao.setSender(userDao);
            messageDao.setReciever(reciever);
            messageDao.setContent(messageContent);
            messageDao.setDate(Calendar.getInstance());

            Gson gson = new Gson();
            String messageGson = gson.toJson(messageDao, MessageDao.class);
            String response = connectToWebservice.addMessage(messageGson);

            if(response.contains("Empty")){
                System.out.println("Failed to register the message!");
            }else{
                reciever="";
                messageContent="";
                System.out.println("Added the message!");
            }
        }
    }
}//class
