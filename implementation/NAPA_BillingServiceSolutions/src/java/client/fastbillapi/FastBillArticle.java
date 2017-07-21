package client.fastbillapi;

/**
 * Created by aldinbradaric on 15/06/17.
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static client.fastbillapi.FastBillArticleModel.createArticleModelFromJSON;

/**
 * This class is a combination of our locally created Plan and Product classes as
 * the values saved within can be later on accessed to determine which plan was used
 * for what product
 */
public class FastBillArticle implements FastBillDecorator{

    private FastBillRequest mRequest;

    protected FastBillArticle(FastBillRequest request) {
        mRequest = request;
    }

    /**
     * getAll makes a Connection to Fastbill-API and recomande all Objects to save in a Arraylist
     * @return
     */

    @Override
    public ArrayList<Object> getAll() {
        mRequest.getAll();

        try{

            JSONObject returnBody = new JSONObject();
            returnBody.put("SERVICE", "article.get");
            returnBody.put("FILTER","");

            StringEntity mEntity = new StringEntity(returnBody.toString(), ContentType.APPLICATION_JSON);
            mRequest.mPost.setEntity(mEntity);


            //actual request
            HttpResponse mResponse = mRequest.mClient.execute(mRequest.mPost);
            HttpEntity mEnt = mResponse.getEntity();
            String response = EntityUtils.toString(mEnt, "UTF-8");

            //check whether connection was valid
            if (mResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new IllegalArgumentException();
            }

            JSONObject mJSON = mRequest.parseJSON(response);
            Object JSONresp = mJSON.get("RESPONSE");
            mJSON = mRequest.parseJSON(JSONresp.toString());

            //get all the saved articles
            JSONArray mJSONArray = (mJSON.getJSONArray("CUSTOMERS"));

            ArrayList<Object> articles = new ArrayList<>(mJSONArray.length());
            //create local article objects from data
            for (Object jObject : mJSONArray) {
                JSONObject mJSONObj = mRequest.parseJSON(jObject.toString());

                FastBillArticleModel article = createArticleModelFromJSON(mJSONObj);

                articles.add(article);

            }

            return articles;

        }catch (Exception e){

        }

        return null;
    }

    /**
     * create prepare a new JasonObject and send it to the Fastbill-API
     * @param object
     */

    @Override
    public void create(Object object) {
        mRequest.create(object);

        try{
            FastBillArticleModel article = (FastBillArticleModel) object;

            JSONObject returnBody = new JSONObject();
            returnBody.put("SERVICE", "article.create");
            JSONObject returnBodyContent = new JSONObject();
            returnBodyContent.put("ARTICLE_NUMBER", article.getArticleNumber());
            returnBodyContent.put("DESCRIPTION", article.getDescription());
            returnBodyContent.put("UNIT_PRICE", article.getUnitPrice());

            returnBody.put("FILTER",returnBodyContent);


            StringEntity mEntity = new StringEntity(returnBody.toString(), ContentType.APPLICATION_JSON);
            mRequest.mPost.setEntity(mEntity);

            //actual request
            HttpResponse mResponse = mRequest.mClient.execute(mRequest.mPost);
            HttpEntity mEnt = mResponse.getEntity();
            String response = EntityUtils.toString(mEnt, "UTF-8");

            //check whether connection was valid
            if (mResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new IllegalArgumentException();
            }
        }catch (Exception e){

        }

    }

    @Override
    public Object getById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
