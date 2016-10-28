package yio.io.sifaapp.Actualizacion;

import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yio.io.sifaapp.Actualizacion.Models.Cliente;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.utils.ConfiguracionServicio;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;
import yio.io.sifaapp.utils.IServicioRemoto;

/**
 * Created by JUANCARLOS on 25/10/2016.
 */
public class UpdateRepositoryImp implements IUpdateRepository {


    Retrofit retrofit = null;
    IServicioRemoto service;
    int contador = 0;
    public UpdateRepositoryImp() {
            if (retrofit == null) {
                Gson gson = new GsonBuilder()
                        .setExclusionStrategies(new ExclusionStrategy() {
                            @Override
                            public boolean shouldSkipField(FieldAttributes f) {
                                return f.getDeclaredClass().equals(ModelAdapter.class);
                            }

                            @Override
                            public boolean shouldSkipClass(Class<?> clazz) {
                                return false;
                            }
                        })
                        .create();
                retrofit = new Retrofit.Builder()
                        .baseUrl(ConfiguracionServicio.getURL())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
    }

    @Override
    public void UpdateCartera() {

    }

    @Override
    public void UpdateCliente() {
        List<Cliente> clientes =  new ArrayList<Cliente>();

        List<Customer> list = new Select().from(Customer.class).where(String.format("offline=1")).queryList();
        for (Customer customer: list) {
            Cliente c = new Cliente();
            c.setApellido1(customer.getApellido1());
            c.setApellido2(customer.getApellido2());
            c.setCedula(customer.getCedula());
            c.setDireccion(customer.getDireccion());
            c.setFechaNacimiento("1987-10-30");
            c.setNombre1(customer.getNombre1());
            c.setNombre2(customer.getNombre2());
            c.setObjCiudadID(customer.getObjCiudadID());
            c.setObjPaisID(customer.getObjPaisID());
            c.setObjGeneroID(customer.getObjGeneroID());
            c.setOrdenCobro(customer.getOrdenCobro());
            c.setObjRutaID(customer.getObjRutaID());
            c.setTelefonos(customer.getTelefonos());
            c.setObjTipoEntradaID(2);
            c.setUsuarioCreacion("ADMIN");
            clientes.add(c);
        }

        if (clientes.size() > 0) {

            contador = clientes.size();

            service = retrofit.create(IServicioRemoto.class);

            for (Cliente cliente : clientes) {

                Call<Integer> call = service.CreateCliente(cliente);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.body()!= 0) {
                            Log.d("UpdateRepository",String.valueOf(response.body()));
                        }
                        if(response.isSuccessful()) {
                            Log.d("UpdateRepository",String.valueOf(response.isSuccessful()));
                        }
                        contador--;
                        postEvent(Events.UpdateClienteContador,null , contador);
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.d("UpdateRepository",String.valueOf(t.getCause()));
                    }
                });
            }
            postEvent(Events.onClienteUpdateSucess,null , "Sincronizando Cartera ...");
        }
    }

    @Override
    public void UpdateVentas() {

    }

    @Override
    public void UpdateDevoluciones() {

    }

    @Override
    public void UpdateEncargos() {

    }

    @Override
    public void GetCLienteContador() {
        List<Customer> list = new Select().from(Customer.class).where(String.format("offline=1")).queryList();
        postEvent(Events.ClienteContador,null,list.size());
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
    private void postEvent(int type, String errorMessage , Object o) {
        Events event = new Events();
        event.setEventype(type);
        if (errorMessage != null) {
            event.setErrorMessage(errorMessage);
        }
        if(o!=null) {
            event.setObject(o);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }

}
