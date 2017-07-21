package client.fastbillapi;

import org.json.JSONObject;

/**
 * Created by Andreas on 17-Jun-17.
 */
public class FastBillArticleModel {

    private String articleId;
    private int articleNumber;
    private String description;
    private double unitPrice;

    /**
     * Constructor for Articel-Model
     * @param articleId
     * @param articleNumber
     * @param description
     * @param unitPrice
     */

    public FastBillArticleModel(String articleId, int articleNumber, String description, double unitPrice) {
        this.articleId = articleId;
        this.articleNumber = articleNumber;
        this.description = description;
        this.unitPrice = unitPrice;
    }

    /**
     * create an Articel-Model from a Jason-Object
     * @param mJSONObj
     * @return
     */

    public static FastBillArticleModel createArticleModelFromJSON(JSONObject mJSONObj){
        String articleId = mJSONObj.get("INVOICE_ITEM_ID").toString();
        int articleNumber = Integer.parseInt(mJSONObj.get("ARTICLE_NUMBER").toString());
        String description = mJSONObj.get("DESCRIPTION").toString();
        double unitPrice = Double.parseDouble(mJSONObj.get("UNIT_PRICE").toString());

        return new FastBillArticleModel(articleId, articleNumber, description,unitPrice);
    }

    /**
     * Getter for ArticelId
     * @return
     */

    public String getArticleId() {
        return articleId;
    }

    /**
     * Getter ArticelNumber
     * @return
     */

    public int getArticleNumber() {
        return articleNumber;
    }

    /**
     * Getter of Descrition
     * @return
     */

    public String getDescription() {
        return description;
    }

    /**
     * Getter of UnitPrice
     * @return
     */

    public double getUnitPrice() {
        return unitPrice;
    }
}
