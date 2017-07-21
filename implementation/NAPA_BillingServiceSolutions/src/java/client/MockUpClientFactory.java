package client;

/**
 * Created by Andreas on 05.06.2017.
 */
public class MockUpClientFactory extends ClientFactory {

    /**
     * create new MockupClient
     * @return
     */

    @Override
    public Client createClient() {
        return new MockUpClient();
    }
}
