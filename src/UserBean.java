import controller.UserController;
import model.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by dani on 2016-11-18.
 * whoohhhhh
 */
@ManagedBean(name="userbean", eager = true)
@SessionScoped
public class UserBean {
    private UserController userController;
    private String username;
    private String password;
    private int age;
    private String address;
    private String workTitle;

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

    public void doLogin(){
        userController = new UserController();
        User user = new User();
       // user.setUsername(username);
       // user.setPassword(password);
        user.setUsername("dani");
        user.setPassword("1234");
        // userController = new UserController();
        userController.login(user);
    }

    public void doRegister(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        user.setAddress(address);
        user.setWorkTitle(workTitle);
        userController.register(user);
    }
}
