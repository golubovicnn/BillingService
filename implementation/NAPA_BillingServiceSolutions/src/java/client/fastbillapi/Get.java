package client.fastbillapi;

/**
 * Created by aldinbradaric on 12/06/17.
 */

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import util.ConfigData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * The idea of this class is to solely focus on getting objects from the FastBill API
 * It's thereby part of the strategy pattern used in the apicall package
 */
public class Get {

    private String url;

    /**
     * getter for URL-API
     */

    public Get() {
        this.url = "https://my.fastbill.com/api/1.0/api.php";
        connectToApi();
    }

    /**
     * inizialised the connection to FastBill-API
     */
    public void connectToApi() {
        String apiMail = ConfigData.getApiMail();
        String apiKey = ConfigData.getApiKey();

        try {
            HttpClient mClient = HttpClientBuilder.create().build();
            HttpPost mPost = new HttpPost(url);

            mPost.addHeader("content-type", "application/json");
            mPost.setEntity(new StringEntity("{" + apiMail + "}:{" + apiKey + "}"));

            HttpResponse mResponse = mClient.execute(mPost);

        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    public void getCustomer() {

    }

    public void getProduct() {

    }

}
