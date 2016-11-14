package yio.io.sifaapp.dialogos.customer;

import android.content.Context;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 15/09/2016.
 */
public class CustomerDialogRepositoryImp implements CustomerDialogRepository {

    private final String TAG = this.getClass().getSimpleName();
    Context context;

    public CustomerDialogRepositoryImp() {}



    @Override
    public void OnFetchData() {
        List<Customer> list = new Select().from(Customer.class).queryList();
        postEvent(Events.onFetchCustomerSucess,list);

    }

    @Override
    public void OnFetchData(int rutaid) {
        List<Customer> list = new Select().from(Customer.class).where(String.format("StbRutaID=%d",rutaid)).queryList();
        postEvent(Events.onFetchCustomerSucess,list);

    }


    private void postEvent(int type) {
        postEvent(type, null);
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

    private void postEvent(int type, Object obj) {
        Events event = new Events();
        event.setEventype(type);
        event.setObject(obj);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }
}
