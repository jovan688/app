package yio.io.sifaapp.Cartera;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

import yio.io.sifaapp.model.CarteraDetalle;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 11/09/2016.
 */
public class productoRepositoryImpl implements  productoRepository {

    public productoRepositoryImpl() {}

    @Override
    public void FetchData() {
        List<Producto> list = null;
        list =new Select().from(Producto.class).queryList();
        postEvent(Events.onFetchDataSucess, list);
    }

    @Override
    public void FetchDatabyCustomerId(int customerId) {
      //  new Select().from(Producto.class).where()
    }

    @Override
    public void FetchDataDetallebyCustomerId(int customerId) {
        List<CarteraDetalle> list = null;

        List<Producto> productos = new ArrayList<Producto>();

        list = new Select().from(CarteraDetalle.class).where(String.format("ClienteID = %d",customerId)).queryList();
        for (CarteraDetalle detalle: list) {
            Producto p = new Select().from(Producto.class).where(String.format("SivProductoID=%d", detalle.getSivProductoID())).querySingle();
            if(p!=null){
                productos.add(p);
            }
        }

        postEvent(Events.onFetchDataSucess, productos);
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
