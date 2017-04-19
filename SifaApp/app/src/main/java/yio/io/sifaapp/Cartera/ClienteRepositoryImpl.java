package yio.io.sifaapp.Cartera;

import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Update;

import java.util.List;
import java.util.Objects;

import yio.io.sifaapp.Actualizacion.Models.Cliente;
import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Customer$Table;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;
import yio.io.sifaapp.utils.SifacDataBase;

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

                String d = Update.table(Cartera.class)
                        .set("OrdenCobro= OrdenCobro + 1 , reordenado= 1")
                        .where(query)
                        .getQuery();
                        //.async()
                        //.execute();

                FlowManager.getDatabase(SifacDataBase.NAME).getWritableDatabase().execSQL(d);

                d = Update.table(Cartera.class)
                        .set("OrdenCobro =" + toPosition + " ,reordenado= 1")
                        .where("ClienteID=" + customer.getClienteID())
                        .getQuery();

                FlowManager.getDatabase(SifacDataBase.NAME).getWritableDatabase().execSQL(d);

                /*******
                customer.setReordenado(true);
                customer.setOrdenCobro(toPosition);
                customer.save();
                *****/

                Log.d("---------------------", "--------------------------" );
                Log.d("---------------Carteras", "Actualizadas------------------" );

                List<Cartera> list =  new Select().from(Cartera.class).where(String.format("reordenado= 1 and StbRutaID = %d" ,customer.getStbRutaID() )).orderBy("OrdenCobro").queryList();
                for (Cartera  c: list ) {
                    Log.d("cartera-> clienteid ", c.getClienteID() + " - "+ c.getNombreCompleto() + " - " + c.getOrdenCobro() + " - "+ c.getReordenado());
                }

                 d = Update.table(Customer.class)
                        .set("newOrden = 1 ,OrdenCobro = OrdenCobro + 1 ")
                        .where(query)
                        .getQuery();
                Log.d("query" , d);

                Log.d("---------------CUSTOMER", "ANTES------------------" );

                List<Customer> list3 =  new Select().from(Customer.class).where(String.format("Customer.ClienteID  in ( select c.ClienteID from Cartera c where  c.reordenado= 1 and  c.StbRutaID = %d ) ", customer.getStbRutaID())).orderBy("OrdenCobro").queryList();
                for (Customer  c: list3 ) {
                    Log.d("Customer antes->", c.getClienteID() + " - " + c.getNombre1() + " - " + c.getOrdenCobro() );
                }

                FlowManager.getDatabase(SifacDataBase.NAME).getWritableDatabase().execSQL(d);


                /*****
                Update.table(Customer.class)
                        //.set("newOrden = 1 , OrdenCobro = COALESCE( (select c.OrdenCobro from Cartera c where  c.reordenado= 1 and c.ClienteID = Customer.ClienteID LIMIT 1) ,OrdenCobro )")
                        .set("newOrden = 1 ,OrdenCobro = OrdenCobro + 1")
                        //.where(String.format("Customer.ClienteID  in ( select c.ClienteID from Cartera c where  c.reordenado= 1 and  c.StbRutaID = %d ) ", customer.getStbRutaID()))
                        .where(query)
                        .async()
                        .execute();
******/



                // Actualizamos el cliente asociado a esta cartera ya que no fue actualizado.
                d = Update.table(Customer.class)
                        .set(String.format("newOrden = 1 , OrdenCobro = %d", toPosition))
                        .where(String.format(Customer$Table.CLIENTEID + "= %d" , customer.getClienteID()))
                        .getQuery();

                FlowManager.getDatabase(SifacDataBase.NAME).getWritableDatabase().execSQL(d);


                Log.d("---------------CUSTOMER", "DESPUES------------------" );

                List<Customer> list2 =  new Select().from(Customer.class).where(String.format("newOrden=1 and StbRutaID = %d" ,customer.getStbRutaID() )).orderBy("OrdenCobro").queryList();
                for (Customer  c: list2 ) {
                    Log.d("c->", c.getClienteID() + " - " + c.getNombre1() + " - " + c.getOrdenCobro() );
                }


            }
            else
            {
                Log.d("executeUpdateOrden" , "fromPosition < toPosition");

               /**** query = String.format("OrdenCobro  >%d And OrdenCobro <= %d and StbRutaID = %d", fromPosition , toPosition , customer.getStbRutaID()); *****/

                query = String.format("OrdenCobro  >%d And OrdenCobro <= %d and StbRutaID = %d", fromPosition , toPosition  , customer.getStbRutaID());

                String d =  Update.table(Cartera.class)
                        .set("OrdenCobro = OrdenCobro - 1 ,reordenado= 1")
                        .where(query)
                        .getQuery();
                        //.async()
                        //.execute();

                FlowManager.getDatabase(SifacDataBase.NAME).getWritableDatabase().execSQL(d);


                 d = Update.table(Cartera.class)
                        .set("OrdenCobro =" + toPosition + " ,reordenado= 1")
                        .where("ClienteID=" + customer.getClienteID())
                        .getQuery();

                FlowManager.getDatabase(SifacDataBase.NAME).getWritableDatabase().execSQL(d);
/**
                customer.setReordenado(true);
                customer.setOrdenCobro(toPosition);
                customer.save(); **/

                Log.d("---------------------", "--------------------------" );
                Log.d("---------------Carteras", "Actualizadas------------------" );

                List<Cartera> list =  new Select().from(Cartera.class).where(String.format("reordenado= 1 and StbRutaID = %d" ,customer.getStbRutaID() )).orderBy("OrdenCobro").queryList();
                for (Cartera  c: list ) {
                    Log.d("cartera-> clienteid ", c.getClienteID() + " - "+ c.getNombreCompleto() + " - " + c.getOrdenCobro() + " - "+ c.getReordenado());
                }

                 d = Update.table(Customer.class)
                        .set("newOrden = 1 ,OrdenCobro = OrdenCobro - 1 ")
                        .where(query)
                        .getQuery();
                Log.d("query" , d);

                Log.d("---------------CUSTOMER", "ANTES------------------" );

                List<Customer> list3 =  new Select().from(Customer.class).where(String.format("Customer.ClienteID  in ( select c.ClienteID from Cartera c where  c.reordenado= 1 and  c.StbRutaID = %d ) ", customer.getStbRutaID())).orderBy("OrdenCobro").queryList();
                for (Customer  c: list3 ) {
                    Log.d("Customer antes->", c.getClienteID() + " - " + c.getNombre1() + " - " + c.getOrdenCobro() );
                }


                FlowManager.getDatabase(SifacDataBase.NAME).getWritableDatabase().execSQL(d);

                /****
                Update.table(Customer.class)
                        .set("newOrden = 1 , OrdenCobro = COALESCE( (select c.OrdenCobro from Cartera c where  c.reordenado= 1 and c.ClienteID = Customer.ClienteID LIMIT 1) ,OrdenCobro )")
                        .where(String.format("StbRutaID=%d and Customer.ClienteID  in ( select c.ClienteID from Cartera c where  c.reordenado= 1 and c.StbRutaID = %d ) ", customer.getStbRutaID(),customer.getStbRutaID()))
                        .async()
                        .execute();

                   ***/


                // Actualizamos el cliente asociado a esta cartera ya que no fue actualizado.
                d= Update.table(Customer.class)
                        .set(String.format("newOrden = 1 , OrdenCobro = %d", toPosition))
                        .where(String.format(Customer$Table.CLIENTEID + "= %d" , customer.getClienteID()))
                        .getQuery();


                FlowManager.getDatabase(SifacDataBase.NAME).getWritableDatabase().execSQL(d);


                Log.d("---------------CUSTOMER", "DESPUES------------------" );

                List<Customer> list2 =  new Select().from(Customer.class).where(String.format("newOrden=1 and StbRutaID = %d" ,customer.getStbRutaID() )).orderBy("OrdenCobro").queryList();
                for (Customer  c: list2 ) {
                    Log.d("c->", c.getClienteID() + " - " + c.getNombre1() + " - " + c.getOrdenCobro() );
                }

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
