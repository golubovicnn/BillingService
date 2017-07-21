package client;

import util.ConfigData;

/**
 * Created by Andreas on 05.06.2017.
 */

/**
 * Factory Pattern for Client
 */
public class ClientFactoryProducer {

    /**
     * make choise witch Factore will be choose
     * @return
     */
    public static ClientFactory getFactory(){

        ClientFactory factoryToReturn;

        //read the config.properties file and choose correct client depending on environment
        switch (ConfigData.getEnvironment()){
            case TEST:
                factoryToReturn = new MockUpClientFactory();
                break;
            case PRODUCTION:
                factoryToReturn = new FastBillClientFactory();
                break;
            default:
                //just in case we simply return the mockup client
                factoryToReturn = new MockUpClientFactory();
                break;
        }
        return factoryToReturn;
    }
}
