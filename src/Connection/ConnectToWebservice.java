package Connection;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by dani on 2016-11-22.
 */
public class ConnectToWebservice {


    public void doGet(){
        Client client = Client.create();
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
    }
}
