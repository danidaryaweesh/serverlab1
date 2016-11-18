import model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by dani on 2016-11-18.
 */
@ManagedBean(name="beanTest", eager = true)
@SessionScoped
public class BeanTest {

    private String username;

    public String getMessage(){
        System.out.println("Hope this works!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        EntityManager em = emf.createEntityManager();
        User user = new User();
        user.setPassword("12345");
        user.setUsername("DarBirc");
        user.setAge(12);
        user.setWorkTitle("Garden");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return "Welcome my dear!";
    }

}
