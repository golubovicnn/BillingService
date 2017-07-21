package client;

import client.fastbillapi.FastBillClient;
import util.ConfigData;

/**
 * Created by Andreas on 05.06.2017.
 */
public class FastBillClientFactory extends ClientFactory{

    /**
     * make a creating-call for Client
     * @return
     */

    @Override
    public Client createClient() {
        return new FastBillClient(ConfigData.getApiKey(), ConfigData.getApiMail());
    }

}
