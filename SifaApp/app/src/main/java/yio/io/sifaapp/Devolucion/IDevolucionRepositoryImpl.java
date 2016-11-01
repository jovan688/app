package yio.io.sifaapp.Devolucion;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import yio.io.sifaapp.model.CarteraDetalle;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Devolucion;
import yio.io.sifaapp.model.DevolucionProductos;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.model.modelSend.Venta;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 23/10/2016.
 */
public class IDevolucionRepositoryImpl implements IDevolucionRepository {


    public IDevolucionRepositoryImpl() {
    }

    @Override
    public void getCliente(int customerid) {
        Customer c = new  Select().from(Customer.class).where(String.format("ClienteID=%d",customerid)).querySingle();
        postEvent(Events.onFetchClienteSucess,c);
    }

    @Override
    public void getList() {
        List<Devolucion> list = new Select().from(Devolucion.class).queryList();
        postEvent(Events.onFetchDevolucionSucess,list);
    }

    @Override
    public void obtenerDetalle(int customerid) {

        List<DevolucionProductos> list = new ArrayList<DevolucionProductos>();

        List<CarteraDetalle> detalle = new Select().from(CarteraDetalle.class).where(String.format("ClienteID=%d", customerid)).queryList();

        for (CarteraDetalle fila: detalle) {
            DevolucionProductos d= new DevolucionProductos();
            d.setClienteID(fila.getClienteID());
            d.setObjSfaFacturaID(fila.objSfaFacturaID);
            d.setSivProductoID(fila.getSivProductoID());
            d.setPrecio(fila.getPrecio());
            list.add(d);
        }
        postEvent(Events.onCarteraDetalleDataSucess,list);
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
