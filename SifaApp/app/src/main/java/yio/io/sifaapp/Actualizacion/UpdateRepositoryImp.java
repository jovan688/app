package yio.io.sifaapp.Actualizacion;

import android.content.Context;
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yio.io.sifaapp.Actualizacion.Models.Cliente;
import yio.io.sifaapp.Actualizacion.Models.ClienteReferencia;
import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Cobro;
import yio.io.sifaapp.model.ContadorModel;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Devolucion;
import yio.io.sifaapp.model.Encargo;
import yio.io.sifaapp.model.EncargoDetalle;
import yio.io.sifaapp.model.ModelConfiguracion;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.model.modelSend.AplFacturasProformaDetalles;
import yio.io.sifaapp.model.modelSend.AplFacturasProformas;
import yio.io.sifaapp.model.modelSend.FacturaProformaDetalle;
import yio.io.sifaapp.model.modelSend.Venta;
import yio.io.sifaapp.model.modelSend.cobro;
import yio.io.sifaapp.model.modelSend.devolucion;
import yio.io.sifaapp.model.modelSend.encargo;
import yio.io.sifaapp.model.modelSend.encargodetalle;
import yio.io.sifaapp.sifacApplication;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;
import yio.io.sifaapp.utils.IServicioRemoto;
import yio.io.sifaapp.utils.Network;
import yio.io.sifaapp.utils.ResponseMessage;

/**
 * Created by JUANCARLOS on 25/10/2016.
 */
public class UpdateRepositoryImp implements IUpdateRepository {


    Retrofit retrofit = null;
    IServicioRemoto service;
    int contador = 0;
    Context context;
    public UpdateRepositoryImp(Context context) {
        this.context = context;

        try {
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
                        .baseUrl(ModelConfiguracion.getURL_SERVER(context))
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
        }
        catch (Exception ex){
            postEvent(Events.onFailToRecoverySession, ex.getMessage());
        }
    }

    @Override
    public void UpdateCartera() {

        Log.d("LLAMANDO A UPDATEACARTERA ", "d");

        ResponseMessage message = Network.isPhoneConnected(context);
        if (message != null && message.isHasError()) {
            postEvent(Events.onNetworkFails, message.getCause() + message.getMessage());
        } else {

            try {
                postEvent(Events.OnMessage, null, "Sincronizando Cartera ...");
                List<Cobro> list = new Select().from(Cobro.class).where(String.format("offline=1")).queryList();
                contador = list.size();
                Log.d("UpdateRepositoryImp ", String.valueOf(contador));
                if (contador == 0) {
                    postEvent(Events.onUpdateCobroSucess, null);
                } else {

                    for (Cobro item : list) {


                        cobro c = new cobro();
                        c.setObjStbRutaID(item.getObjStbRutaID());
                        c.setAbono(item.getAbono());
                        c.setCancelo(item.getCancelo());
                        String fech = new SimpleDateFormat("yyyy-MM-dd").format(item.getFechaAbono());
                        c.setFechaAbono(fech);
                        c.setMontoAbonado(item.getMontoAbonado());
                        c.setMotivoNoAbono(item.getMotivoNoAbono());
                        c.setCedula(item.getCedula());
                        c.setSaldo(item.getSaldo());
                        c.setUsuarioCreacion(String.valueOf(item.getUsuarioCreacion()));
                        c.setObjCobradorID(item.getObjCobradorID());
                        c.setObjSccCuentaID(String.valueOf(item.getObjSccCuentaID()));
                        c.setParamVerificacion(String.valueOf(item.getId()));

                        Call<String> call = service.CreateCobro(c);

                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                contador--;
                                if (!response.body().equals("-1")) {
                                    int id = Integer.parseInt(response.body().split("\\|")[0]);
                                    if (id != 0) {
                                        String ced = response.body().split("\\|")[1];
                                        Cobro cobro1 = new Select().from(Cobro.class).where(String.format("id=%d", Integer.parseInt(ced))).querySingle();
                                        if (cobro1 != null) {
                                            cobro1.setOffline(false);
                                            cobro1.save();
                                            new Delete().from(Cartera.class).where(String.format("SccCuentaID=%d", cobro1.getObjSccCuentaID())).query();
                                            new Delete().from(Cobro.class).where(String.format("id=%d", cobro1.getId())).query();
                                            Log.d("REGRESO DE UPDATEACARTERA id =>", ced);
                                        }
                                    }
                                }
                                if (contador == 0) {
                                    postEvent(Events.onUpdateCobroSucess, null);
                                } else {

                                    postEvent(Events.UpdateCobroContador, null, contador);
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                contador--;
                                Log.d("UpdateRepository", String.valueOf(t.getCause()));
                                postEvent(Events.OnMessage, null, t.getMessage());
                            }
                        });
                    }
                }

                //postEvent(Events.onUpdateCobroSucess, null);
                // postEvent(Events.OnMessage, null, "Sincronizando Cobros ...");

            } catch (Exception ex) {
                postEvent(Events.OnMessage, null, ex.getMessage());
            }
        }
    }

    @Override
    public void UpdateCliente() {

        ResponseMessage message = Network.isPhoneConnected(context);
        if(message!=null && message.isHasError()){
            postEvent(Events.onNetworkFails,message.getCause() + message.getMessage());
        }
        else
        {
            postEvent(Events.OnMessage, null, "Sincronizando Clientes ...");
            // Datos a enviar
            List<Cliente> clientes = new ArrayList<Cliente>();

            List<Customer> list = new Select().from(Customer.class).where(String.format("offline=1")).queryList();

            if(list.size() == 0 ) {
                postEvent(Events.onClienteUpdateSucess, null);
            }
            else
            {
                for (Customer customer : list) {
                     Cliente c = new Cliente();
                    c.setApellido1(customer.getApellido1());
                    c.setApellido2(customer.getApellido2());
                    c.setCedula(customer.getCedula());
                    c.setDireccion(customer.getDireccion());
                    c.setNombre1(customer.getNombre1());
                    c.setNombre2(customer.getNombre2());
                    c.setObjCiudadID(customer.getObjCiudadID());
                    c.setObjPaisID(customer.getObjPaisID());
                    c.setObjGeneroID(customer.getObjGeneroID());
                    c.setOrdenCobro(customer.getOrdenCobro());
                    c.setObjRutaID(customer.getStbRutaID());
                    c.setNumeroTelefono(customer.getTelefonos());
                    c.setObjTipoEntradaID(2);
                    // Nuevo CAMBIO
                    c.setUsuarioCreacion(((sifacApplication) context.getApplicationContext()).getUsuarioName());
                    c.setParamVerificacion(customer.getCedula());
                    clientes.add(c);
                }

                contador = clientes.size();

                service = retrofit.create(IServicioRemoto.class);

                for (Cliente cliente : clientes) {

                    Call<String> call = service.CreateCliente(cliente);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            contador--;
                            if(!response.body().equals("-1")) {
                                int id = Integer.parseInt(response.body().split("\\|")[0]);
                                if (id != 0) {
                                    // actualizar offline
                                    String ParamVerificacion = response.body().split("\\|")[1];
                                    Customer customer = new Select().from(Customer.class).where(String.format("Cedula = '%s'", ParamVerificacion)).querySingle();
                                    customer.setOffline(false);
                                    customer.save();

                                }
                            }
                                if (contador == 0) {
                                    postEvent(Events.onClienteUpdateSucess, null);
                                } else {
                                    postEvent(Events.UpdateClienteContador, null, contador);
                                }

                            Log.d("UpdateRepository", String.valueOf(response.body()));
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            contador--;
                            Log.d("UpdateRepository", String.valueOf(t.getCause()));
                            postEvent(Events.OnMessage, null, t.getMessage());
                            if (contador == 0) {
                                postEvent(Events.onClienteUpdateSucess, null);
                            }
                        }
                    });
                }

            }
        }
    }

    @Override
    public void UpdateVentas() {
        ResponseMessage message = Network.isPhoneConnected(context);
        if(message!=null && message.isHasError()){
            postEvent(Events.onNetworkFails,message.getCause() + message.getMessage());
        }
        else {
            postEvent(Events.OnMessage, null, "Sincronizando Ventas ...");

            List<Venta> list = new Select().from(Venta.class).where(String.format("offline=1")).queryList();
            contador = list.size();
            if(service== null)
                service = retrofit.create(IServicioRemoto.class);

            if (contador == 0) {
                postEvent(Events.onVentasUpdateSucess, null);
            }
            else {
                for (Venta item : list) {

                    AplFacturasProformas venta = new AplFacturasProformas();
                    venta.setCedula(item.getCedula());
                    venta.setNuevoCredito(item.getNuevaventa());
                    venta.setObjVendedorID(item.getObjVendedorID());
                    venta.setObjEstadoID(item.getObjEstadoID());
                    venta.setObjTerminoPagoID(item.objTerminoPagoID);
                    venta.setObjModalidadPagoID(item.getObjModalidadPagoID());
                    venta.setObjDescuentoID(item.getObjDescuentoID());
                    venta.setFecha(item.getFecha());
                    venta.setSubtotal(item.getSubtotal());
                    venta.setDescuento(item.getDescuento());
                    venta.setTotal(item.getTotal());
                    venta.setPrima(item.getPrima());
                    venta.setSaldo(item.getSaldo());
                    venta.setObservaciones(item.getObservaciones());
                    venta.setUsuarioCreacion(String.valueOf(item.getUsuarioCreacion()));
                    venta.setParamVerificacion(String.valueOf(item.getVentaID()));

                    List<AplFacturasProformaDetalles> listdetalle = new ArrayList<AplFacturasProformaDetalles>();

                    for (FacturaProformaDetalle proforma:item.getFacturaDetalle()) {
                        AplFacturasProformaDetalles d = new AplFacturasProformaDetalles();
                        d.setObjSivProductoID(proforma.getObjSivProductoID());
                        d.setCantidad(proforma.getCantidad());
                        d.setPrecio(proforma.getPrecio());
                        d.setSubtotal(proforma.getSubtotal());
                        d.setDescuento(proforma.getDescuento());
                        d.setTotal(proforma.getTotal());
                        listdetalle.add(d);
                    }
                    venta.setFacturaProformaDetalle(listdetalle);

                    Call<String> call = service.CreateVentas(venta);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            contador--;
                            if(!response.body().equals("-1")) {
                                int id = Integer.parseInt(response.body().split("\\|")[0]);
                                if (id != 0) {
                                    // actualizamos offline
                                    int ventaid = Integer.parseInt(response.body().split("\\|")[1]);
                                    Venta venta1 = new Select().from(Venta.class).where(String.format("VentaID=%d", ventaid)).querySingle();
                                    venta1.setOffline(false);
                                    venta1.save();

                                    new Delete().from(Venta.class).where(String.format("VentaID=%d", ventaid)).query();
                                }
                            }
                            if (contador == 0) {
                                postEvent(Events.onVentasUpdateSucess, null);
                            } else {

                                postEvent(Events.UpdateVentasContador, null, contador);
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            contador--;
                            Log.d("UpdateRepository", String.valueOf(t.getCause()));
                            postEvent(Events.OnMessage, null, t.getMessage());
                        }
                    });
                }
            }
            //postEvent(Events.OnMessage, null, "Sincronizando Ventas ...");
        }
    }

    @Override
    public void UpdateDevoluciones() {
        ResponseMessage message = Network.isPhoneConnected(context);
        if(message!=null && message.isHasError()){
            postEvent(Events.onNetworkFails,message.getCause() + message.getMessage());
        }
        else {

            postEvent(Events.OnMessage, null, "Sincronizando Devoluciones ...");
            List<Devolucion> list = new Select().from(Devolucion.class).where(String.format("offline=1")).queryList();
            contador = list.size();
            if (contador == 0) {
                postEvent(Events.onDevolucionesUpdateSucess, null);
            }
            else
            {
                for (Devolucion item : list) {
                    devolucion dev = new devolucion();
                    dev.setCedula(item.getCedula());
                    dev.setUsuarioCreacion(item.getUsuarioCreacion().toString());
                    dev.setFecha(item.getFecha().toString());
                    dev.setObjSccCuentaID(item.getObjSccCuentaID());
                    dev.setObjSfaFacturaID(item.getObjSfaFacturaID());
                    dev.setObjSivProductoID(item.getObjSivProductoID());
                    dev.setObjStbRutaID(item.getObjStbRutaID());
                    dev.setObjVendedorID(item.getObjVendedorID());
                    dev.setRazonDevolucion(item.getRazonDevolucion());
                    dev.setTotalDevolucion(item.getTotalDevolucion());
                    // nuestro parametro
                    dev.setParamVerificacion(String.valueOf(item.getId()));

                    Call<String> call = service.CreateDevoluciones(dev);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            contador--;
                            if(!response.body().equals("-1")) {
                                int id = Integer.parseInt(response.body().split("\\|")[0]);
                                if (id != 0) {
                                    // offline
                                    int devid =  Integer.parseInt(response.body().split("\\|")[1]);
                                    Devolucion row = new Select().from(Devolucion.class).where(String.format("Id=%d", devid)).querySingle();
                                    row.setOffline(false);
                                    row.save();

                                    new Delete().from(Devolucion.class).where(String.format("Id=%d", devid)).query();
                                }
                            }
                            if (contador == 0) {
                                postEvent(Events.onDevolucionesUpdateSucess, null);
                            } else {
                                postEvent(Events.UpdateDevolucionesContador, null, contador);
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("UpdateRepository", String.valueOf(t.getCause()));
                            contador--;
                            postEvent(Events.OnMessage, null, t.getMessage());
                        }
                    });
                }
            }

            //postEvent(Events.OnMessage, null, "Sincronizando Devoluciones ...");
        }
    }

    @Override
    public void UpdateEncargos() {
        ResponseMessage message = Network.isPhoneConnected(context);
        if(message!=null && message.isHasError()){
            postEvent(Events.onNetworkFails,message.getCause() + message.getMessage());
        }
        else {
            postEvent(Events.OnMessage, null, "Sincronizando Encargo... ");
            List<Encargo> list = new Select().from(Encargo.class).where(String.format("offline=1")).queryList();
            contador = list.size();

            if (contador == 0) {
                postEvent(Events.onEncargoUpdateSucess, null);
            }
            else
            {


                for (Encargo item : list) {
                    encargo _encargo = new encargo();
                    _encargo.setCedula(item.getCedula());
                    _encargo.setObjSrhEmpleadoID(item.getObjSrhEmpleadoID().toString());
                    //_encargo.setUsuarioCreacion(item.getUsuarioCreacion().toString());
                    // NUEVO CAMBIO
                    _encargo.setUsuarioCreacion(((sifacApplication) context.getApplicationContext()).getUsuarioName());
                    _encargo.setParamVerificacion(String.valueOf(item.getId()));

                    List<encargodetalle> detail = new ArrayList<encargodetalle>();

                    for (EncargoDetalle det : item.getdetalle()) {

                        encargodetalle detalle = new encargodetalle();
                        detalle.setNombre_Producto(det.getNombre_Producto());
                        detalle.setObjCategoriaID(det.getObjCategoriaID());
                        detalle.setObjProductoID(det.getProductoID());
                        detalle.setObservaciones(det.getObservaciones());
                        detail.add(detalle);
                        //_encargo.getEncargoDetalle().add(detalle);
                    }

                    _encargo.setEncargoDetalle(detail);

                    Call<String> call = service.CreateEncargo(_encargo);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            contador--;
                            if(!response.body().equals("-1")) {
                                int id = Integer.parseInt(response.body().split("\\|")[0]);
                                if (id != 0) {
                                    int encargoID = Integer.parseInt(response.body().split("\\|")[1]);
                                    Encargo encargo = new Select().from(Encargo.class).where(String.format("id=%d", encargoID)).querySingle();
                                    encargo.setOffline(false);
                                    encargo.save();
                                    new Delete().from(Encargo.class).where(String.format("Id=%d", encargoID)).query();
                                }
                            }
                            if (contador == 0) {
                                postEvent(Events.onEncargoUpdateSucess, null);
                            } else {
                                postEvent(Events.UpdateEncargosContador, null, contador);
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("UpdateRepository", String.valueOf(t.getCause()));
                            postEvent(Events.OnMessage, null, t.getMessage());
                            contador--;
                        }
                    });
                }


            }
        }
    }

    @Override
    public void GetCLienteContador() {
        List<Customer> list = new Select().from(Customer.class).where(String.format("offline=1")).queryList();
        postEvent(Events.ClienteContador,null,list.size());
    }

    @Override
    public void CountOfflineData() {
        int clientes  = (int) new Select().from(Customer.class).where(String.format("offline=1")).queryList().size();
        int carteras  = (int) new Select().from(Cobro.class).where(String.format("offline=1")).queryList().size();
        int encargos  = (int) new Select().from(Encargo.class).where(String.format("offline=1")).queryList().size();
        int devoluciones  = (int) new Select().from(Devolucion.class).where(String.format("offline=1")).queryList().size();
        int ventas  = (int) new Select().from(Venta.class).where(String.format("offline=1")).queryList().size();
        int referencia  = (int) new Select().from(Customer.class).where(String.format("offlineReferencia=1")).queryList().size();

        ContadorModel contador = new ContadorModel();
        contador.setCartera(carteras);
        contador.setClientes(clientes);
        contador.setDevoluciones(devoluciones);
        contador.setEncargos(encargos);
        contador.setVentas(ventas);
        contador.setReferencia(referencia);
        postEvent(Events.CargarContadores,null,contador);


    }

    @Override
    public void UpdateClienteReferencia() {
        ResponseMessage message = Network.isPhoneConnected(context);
        if(message!=null && message.isHasError()){
            postEvent(Events.onNetworkFails,message.getCause() + message.getMessage());
        }
        else
        {
            postEvent(Events.OnMessage, null, "Sincronizando Referencias ...");
            // Datos a enviar
            List<ClienteReferencia> referencias = new ArrayList<ClienteReferencia>();

            List<Customer> list2  = new Select().from(Customer.class).where(String.format("offlineReferencia=1")).queryList();

            contador = list2.size();

            if(list2.size() == 0 ) {
                postEvent(Events.onReferenceClienteUpdateSucess, null);
            }
            else {
                for (Customer customer : list2) {
                    ClienteReferencia c = new ClienteReferencia();
                    c.setClienteID(customer.getClienteID());
                    c.setReferencia(customer.getReferencia());
                    referencias.add(c);
                }
                if(service==null)
                    service = retrofit.create(IServicioRemoto.class);

                for (ClienteReferencia cliente : referencias) {

                    Call<Integer> call =  service.ActualizarRefCliente(cliente);
                    call.enqueue(new Callback<Integer>() {

                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            contador--;
                            if(response.body()!=null) {
                                int id = Integer.parseInt(response.body().toString());
                                if (id != 0) {
                                    Customer customer = new Select().from(Customer.class).where(String.format("ClienteID = %d", id)).querySingle();
                                    if(customer!=null) {
                                        customer.setOfflineReferencia(false);
                                        customer.save();
                                    }
                                }
                            }
                            if (contador == 0) {
                                postEvent(Events.onReferenceClienteUpdateSucess, null);
                            } else {
                                postEvent(Events.UpdateClienteReferenciaContador, null, contador);
                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            contador--;
                            Log.d("UpdateRepository", String.valueOf(t.getCause()));
                            postEvent(Events.OnMessage, null, t.getMessage());
                            if (contador == 0) {
                                postEvent(Events.onReferenceClienteUpdateSucess, null);
                            }
                        }
                    });

                    //postEvent(Events.onReferenceClienteUpdateSucess, null);
                }
                //postEvent(Events.onReferenceClienteUpdateSucess, null);
            }

        }
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
