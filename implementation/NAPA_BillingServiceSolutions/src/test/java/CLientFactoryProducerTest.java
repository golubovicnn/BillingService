import client.ClientFactoryProducer;
import client.MockUpClientFactory;
import org.junit.Test;
import static org.junit.Assert.assertSame;

/**
 * Created by Andreas on 05.06.2017.
 */
public class CLientFactoryProducerTest {

    //tests if the right class is produced depending on environment
    @Test
    public void environmentTest(){

        assertSame("This should be a MockupClient", MockUpClientFactory.class, ClientFactoryProducer.getFactory().getClass());
    }


    @Test
    public void getAllCustomersTest(){

    }

}
