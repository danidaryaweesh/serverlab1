package Connection;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by dani on 2016-11-22.
 */
public class ConnectToWebservice {

    private RestClient restClient;

    public ConnectToWebservice(){
        restClient = new RestClient();
    }

    public String loginRequest(String toSend){
        System.out.println(toSend);
        String url = "";
        String string="";
        try {
            url = "http://130.229.145.151:8080/rest/user/login?userDaoJson=" + URLEncoder.encode(toSend, "UTF-8");
            Resource resource = restClient.resource(url);
            string = resource.accept("text/plain").get(String.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return string;
    }

    public String addUser(String user){

        System.out.println(user);
        String url = "";
        String string="";
        try {
            url = "http://130.229.145.151:8080/rest/user?" + URLEncoder.encode(user, "UTF-8");
            Resource resource = restClient.resource(url);
            string = resource.accept("text/plain").get(String.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return string;
    }

    public String getCertainUserWithID(int id){
        Resource resource = restClient.resource("http://130.229.145.151:8080/rest/user/"+id);
        String string = resource.accept("text/plain").get(String.class);

        return string;
    }

    public String getCertainUserWithUsername(String username){
        Resource resource = restClient.resource("http://130.229.145.151:8080/rest/user/username/"+username);
        String string = resource.accept("text/plain").get(String.class);
        return string;
    }


    public String addLog(String log){
        Resource resource = restClient.resource("http://130.229.145.151:8080/rest/user/add?"+log);
        ClientResponse clientResponse = resource.accept("text/plain").post(String.class);

        return clientResponse.toString();
    }
}
