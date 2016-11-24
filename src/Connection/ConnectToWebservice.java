package Connection;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
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
        try {
            url = "http://130.229.145.151:8080/rest/user/login?userDaoJson=" + URLEncoder.encode(toSend, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //http://130.229.145.151:8080/rest/user/login?userDaoJson=
        Resource resource = restClient.resource(url);//"http://130.229.145.151:8080/rest/user/login/?userDaoJson="+toSend
        String string = resource.accept("text/plain").get(String.class);

        return string;
    }

    public String addUser(String user){
        Resource resource = restClient.resource("http://130.229.145.151:8080/rest/user?"+user);
        ClientResponse clientResponse = resource.accept("text/plain").post(String.class);
        String string = clientResponse.toString();

        return string;
    }

    public String getCertainUserWithID(int id){
        Resource resource = restClient.resource("http://130.229.145.151:8080/rest/user/"+id);
        String string = resource.accept("text/plain").get(String.class);

        return string;
    }

    public String getCertainUserWithUsername(String username){
        Resource resource = restClient.resource("http://130.229.145.151:8080/rest/user/"+username);
        String string = resource.accept("text/plain").get(String.class);

        return string;
    }

    // måste ändras till post senare!
    public String addLog(String log){
        Resource resource = restClient.resource("http://130.229.145.151:8080/rest/user/add?"+log);
        ClientResponse clientResponse = resource.accept("text/plain").post(String.class);

        return clientResponse.toString();
    }

/*
    jersey-client --> not needed right now!
    public void doGet(){

        WebResource webResource= client.resource("http://130.229.136.250:8080/rest/hello/");
        ClientResponse response = webResource.accept("text/plain")
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);

        System.out.println("Output from Server .... \n");
        System.out.println(output);
    }

    public void doPost(String toSend){
        Client client = Client.create();
        WebResource webResource= client.resource("http://130.229.136.250:8080/rest/hello/");
        ClientResponse response = webResource.accept("text/plain") // kan skickas som jSon --> (application/json)
                .post(ClientResponse.class, toSend);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);

        System.out.println("Output from Server .... \n");
        System.out.println(output);
    }*/

   /* public void test(){
        RestClient client = new RestClient();
        Resource resource = client.resource("http://130.229.136.250:8080/rest/hello/");
        String string = resource.accept("text/plain").get(String.class);
        resource.g
    }*/
}
