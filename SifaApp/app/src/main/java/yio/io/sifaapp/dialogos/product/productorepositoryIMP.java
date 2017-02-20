package yio.io.sifaapp.dialogos.product;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import yio.io.sifaapp.model.CarteraDetalle;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
public class productorepositoryIMP implements  productorepository {


    List<Producto> productos = new ArrayList<Producto>();

    public productorepositoryIMP() {}

    @Override
    public void getAllProducts() {
        productos = new Select().from(Producto.class).where(String.format("Activo=1")).queryList();
        postEvent(Events.onFetchDataSucess, productos);
    }

    @Override
    public void getProductsByCustomerId(String cedula, boolean activos) {
        List<CarteraDetalle> detalle = new Select().from(CarteraDetalle.class).where(String.format("cedula='%s'",cedula)).queryList();
        for (CarteraDetalle fila: detalle) {
            int activo = activos ? 1 : 0;
            Producto producto= new Select().from(Producto.class).where(String.format("SivProductoID=%d", fila.getSivProductoID() , activo)).querySingle();
            if(producto!=null)
                productos.add(producto);
        }

        postEvent(Events.onFetchDataSucess, productos);
    }

    /*
    @Override
    public void getProductsByCustomerId(long customerId, boolean activos) {
        List<CarteraDetalle> detalle = new Select().from(CarteraDetalle.class).where(String.format("ClienteID=%d",customerId)).queryList();
        for (CarteraDetalle fila: detalle) {
            int activo = activos ? 1 : 0;
            Producto producto= new Select().from(Producto.class).where(String.format("SivProductoID=%d", fila.getSivProductoID() , activo)).querySingle();
            if(producto!=null)
                productos.add(producto);
        }

        postEvent(Events.onFetchDataSucess, productos);
    } */

    @Override
    public void getCarteraDetalleByCustomerId(long customerId) {
        List<CarteraDetalle> detalle = new Select().from(CarteraDetalle.class).where(String.format("ClienteID=%d", customerId)).queryList();
        postEvent(Events.onCarteraDetalleDataSucess, detalle);
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
