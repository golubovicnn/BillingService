package client.fastbillapi;

/**
 * Created by aldinbradaric on 12/06/17.
 */

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import util.ConfigData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * The idea of this class is to solely focus on posting objects to the FastBill API
 * It's thereby part of the strategy pattern used in the apicall package
 */
public class Post {

    private String url;

    /**
     * get FastBill -URL
     */

    public Post() {
        this.url = "https://my.fastbill.com/api/1.0/api.php";
    }


    /**
     * inizializied the Connection to FastBill -API
     */
    public void connectToApi() {
        String apiMail = ConfigData.getApiMail();
        String apiKey = ConfigData.getApiKey();
        System.out.println(apiMail + " " + apiKey);
        String hashedValue = Base64.getEncoder().encodeToString((apiMail + apiKey).getBytes());
        System.out.println(hashedValue);
        try {
            HttpClient mClient = HttpClientBuilder.create().build();

            HttpPost mPost = new HttpPost(url);
            //StringEntity mEntity = new StringEntity("{E-Mail-Address: " + apiMail + "} : {API-Key: " + apiKey + "}");

            //pass the hash value of our FB mail + key
            mPost.addHeader("content-type", "application/json");
            mPost.setHeader("Authorization", "Basic " + apiMail + apiKey);
            //mPost.setEntity(mEntity);

            HttpResponse mResponse = mClient.execute(mPost);
            //debug
            System.out.println(mResponse.toString());

        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    public void postCustomer() {

    }

    public void postProduct() {
        connectToApi();
    }
}
