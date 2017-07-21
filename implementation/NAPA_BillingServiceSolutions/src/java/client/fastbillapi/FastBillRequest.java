package client.fastbillapi;

/**
 * Created by aldinbradaric on 15/06/17.
 */

import org.apache.http.Header;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import util.ConfigData;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * The idea of this class is to build the requests needed for every API call
 */
public class FastBillRequest implements FastBillRequestInterface{
    protected HttpPost mPost;
    protected HttpClient mClient;

    public FastBillRequest() {

    }

    /**
     * prepare Request to get all Objects from Fastbill
     * @return
     */
    public ArrayList<Object> getAll() {
        prepareRequest();

        return null;
    }

    /**
     * prepare Requeststatement to create a new Object on Fastbill
     * @param object
     */
    public void create(Object object){
        prepareRequest();
    }

    /**
     * prepare Request-Statement to get Object by Id from FastBill
     * @param id
     * @return
     */

    public Object getById(String id) {
        prepareRequest();
        return null;
    }

    /**
     * prepare Requeststatement to delete an Object on fastbill
     * @param id
     */

    public void delete(String id) {
        prepareRequest();
    }

    /**
     * prepare an Standard-Request for FastBill-API
     */

    protected void prepareRequest() {
        try {
            mPost = new HttpPost(ConfigData.getApiEndPoint());
            mClient = HttpClientBuilder.create().build();

            //header set-up + authentication
            CredentialsProvider mProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials mCreds = new UsernamePasswordCredentials(ConfigData.getApiMail(), ConfigData.getApiKey());

            mProvider.setCredentials(AuthScope.ANY, mCreds);
            Header header = new BasicScheme(StandardCharsets.UTF_8).authenticate(mCreds, mPost, null);

            mPost.addHeader(header);
            HttpClient mClient = HttpClientBuilder.create().build();
            //header set-up goes till here
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Created the JSON body for the API request
     * @return the parsed JSON object (ie. String)
     */
    public String createJSON(){
        return new JSONObject().put("SERVICE", "customer.get").put("FILTER", "").toString();
    }

    /**
     * Takes a String
     * @param string
     * @return the parsed String (ie. JSONObject)
     */
    public JSONObject parseJSON(String string) {
        return new JSONObject(string);
    }
}
