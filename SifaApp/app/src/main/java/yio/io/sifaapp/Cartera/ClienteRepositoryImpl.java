package yio.io.sifaapp.Cartera;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 18/10/2016.
 */
public class ClienteRepositoryImpl implements IClienteRepository {

    public  ClienteRepositoryImpl(){

    }

    @Override
    public void getCliente(int customerid) {
        Customer list = null;
        list =new Select().from(Customer.class).where(String.format("ClienteID = %d",customerid)).querySingle();
        postEvent(Events.onFetchClienteSucess, list);
    }


    private void postEvent(int type, Object obj) {
        Events event = new Events();
        event.setEventype(type);
        event.setObject(obj);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }

    private void postEvent(int type, String errorMessage) {
        Events event = new Events();
        event.setEventype(type);
        if (errorMessage != null) {
            event.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }
}
