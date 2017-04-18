package yio.io.sifaapp.Cartera;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Update;

import java.util.List;
import java.util.Objects;

import yio.io.sifaapp.Actualizacion.Models.Cliente;
import yio.io.sifaapp.model.Cartera;
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

    @Override
    public void executeUpdateOrden(int fromPosition, int toPosition, Cartera customer) {
        try
        {
            String query = "";

            if (fromPosition > toPosition) {
                Log.d("executeUpdateOrden" , "fromPosition > toPosition");

                query =String.format("OrdenCobro  >=%d And OrdenCobro < %d and StbRutaID = %d", toPosition, fromPosition , customer.getStbRutaID());

                Update.table(Cartera.class)
                        .set("OrdenCobro= OrdenCobro + 1")
                        .where(query)
                        .async()
                        .execute();

                query =String.format("c.OrdenCobro  >=%d And c.OrdenCobro < %d and c.StbRutaID = %d", toPosition + 1, fromPosition + 1, customer.getStbRutaID());

                String clause = "ClienteID  in ( select c.ClienteID from Cartera c where " + query + " ) ";


                Update.table(Customer.class)
                        .set("newOrden = 1 , OrdenCobro = ( select c.OrdenCobro from Cartera c where  c.ClienteID = ClienteID and " + query + " ) ")
                        .where(clause)
                        .async()
                        .execute();


/*
                Update.table(Customer.class)
                        .set("OrdenCobro= OrdenCobro + 1 , newOrden = 1")
                        .where(clause)
                        .async()
                        .execute();
                        */
                /*
                List<Cartera> list = new Select().from(Cartera.class).where(String.format("OrdenCobro  >=%d And OrdenCobro < %d and StbRutaID = %d", toPosition, fromPosition , customer.getStbRutaID())).queryList();
                for (Cartera i : list) {
                    int orden = i.getOrdenCobro() + 1;
                    //i.setNewOrden(true);
                    i.setOrdenCobro(orden);
                    i.save();

                    Customer cliente = new Select().from(Customer.class).where(String.format("ClienteID=%d",customer.getClienteID())).querySingle();
                    if(cliente!=null){
                        cliente.setOrdenCobro(orden);
                        cliente.setNewOrden(true);
                        cliente.save();
                    }
                }
                */
                customer.setOrdenCobro(toPosition);
                customer.save();

            }
            else
            {
                Log.d("executeUpdateOrden" , "fromPosition < toPosition");

                query = String.format("OrdenCobro  >%d And OrdenCobro <= %d and StbRutaID = %d", fromPosition , toPosition , customer.getStbRutaID());

                Update.table(Cartera.class)
                        .set("OrdenCobro = OrdenCobro - 1")
                        .where(query)
                        .async()
                        .execute();


                query = String.format("c.OrdenCobro  >%d And c.OrdenCobro <= %d and c.StbRutaID = %d", fromPosition - 1 , toPosition - 1, customer.getStbRutaID());

                String clause = "ClienteID  in ( select c.ClienteID from Cartera c where " + query + " ) ";



                Update.table(Customer.class)
                        .set("newOrden = 1 , OrdenCobro = ( select c.OrdenCobro from Cartera c where  c.ClienteID = ClienteID and " + query + " ) ")
                        .where(clause)
                        .async()
                        .execute();



                /*
                List<Cartera> list = new Select().from(Cartera.class).where(String.format("OrdenCobro  >%d And OrdenCobro <= %d and StbRutaID = %d", fromPosition , toPosition , customer.getStbRutaID())).queryList();
                for (Cartera i : list) {
                    int orden = i.getOrdenCobro() -1;
                    //i.setNewOrden(true);
                    i.setOrdenCobro(orden);
                    i.save();
                   // Log.d("executeUpdateOrden" , String.valueOf(orden));
                    Customer cliente = new Select().from(Customer.class).where(String.format("ClienteID=%d",customer.getClienteID())).querySingle();
                    if(cliente!=null){
                        cliente.setOrdenCobro(orden);
                        cliente.setNewOrden(true);
                        cliente.save();
                    }
                }
                */
                customer.setOrdenCobro(toPosition);
                customer.save();
            }
            postEvent(Events.onSyncCLienteOrden, true);
        }
        catch (Exception ex){
            postEvent(Events.onSyncOrdenERROR,ex.getMessage() , null);

        }
    }


    private void postEvent(int type, Object obj) {
        Events event = new Events();
        event.setEventype(type);
        event.setObject(obj);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }

    private void postEvent(int type, String errorMessage , Objects obj) {
        Events event = new Events();
        event.setEventype(type);
        if (errorMessage != null) {
            event.setErrorMessage(errorMessage);
        }
        event.setObject(obj);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }
}
