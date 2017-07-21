package client.fastbillapi;

import java.util.ArrayList;

/**
 * Created by aldinbradaric on 15/06/17.
 */
public interface FastBillRequestInterface {


    public ArrayList<Object> getAll();

    public void create(Object object);

    public Object getById(String id);

    public void delete(String id);
}
