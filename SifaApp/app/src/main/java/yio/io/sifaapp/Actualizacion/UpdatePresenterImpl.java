package yio.io.sifaapp.Actualizacion;

import android.content.Context;
import android.util.Log;

import yio.io.sifaapp.model.ContadorModel;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;
import yio.io.sifaapp.utils.TypeCounter;

/**
 * Created by JUANCARLOS on 25/10/2016.
 */
public class UpdatePresenterImpl implements IUpdatePresenter {

    IUpdateView view;
    private EventBus eventbus;
    private IUpdateInteractor interactor;
    Context context;

    public UpdatePresenterImpl(IUpdateView view ,Context context) {
        this.view = view;
        this.eventbus = GreenRobotEventBus.getInstance();

        if(this.interactor == null)
            this.interactor = new UpdateInteractorImpl(context);
    }



    @Override
    public void onCreated() {
        if(eventbus.isRegistered(this))
            eventbus.unregister(this);

        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
        eventbus.unregister(this);
    }


    @Override
    public void UpdateCartera() {
        interactor.UpdateCartera();
    }

    @Override
    public void UpdateCliente() {
        interactor.UpdateCliente();
        //view.disableButtons();
    }

    @Override
    public void UpdateVentas() {
        interactor.UpdateVentas();
    }

    @Override
    public void UpdateDevoluciones() {
        interactor.UpdateDevoluciones();
    }

    @Override
    public void UpdateEncargos() {
        interactor.UpdateEncargos();
    }

    @Override
    public void onEventMainThread(Events event) {
        switch (event.getEventype()){
            case Events.UpdateClienteReferenciaContador :
                view.UpdateCounter(TypeCounter.REFERENCIA,(int)event.getObject());
                break;
            case Events.UpdateClienteContador :
                view.UpdateCounter(TypeCounter.CLIENTE,(int)event.getObject());
                break;
            case Events.UpdateEncargosContador :
                view.UpdateCounter(TypeCounter.ENCARGO,(int)event.getObject());
                break;
            case Events.UpdateCarteraContador :
                view.UpdateCounter(TypeCounter.COBRO,(int)event.getObject());
                break;
            case Events.UpdateDevolucionesContador:
                view.UpdateCounter(TypeCounter.DEVOLUCION,(int)event.getObject());
                break;
            case Events.UpdateVentasContador:
                view.UpdateCounter(TypeCounter.VENTA,(int)event.getObject());
                break;
            case Events.ClienteContador:
                view.ClienteCounter((int)event.getObject());
                break;
            case Events.CargarContadores:
                view.CountOfflineData((ContadorModel) event.getObject());
                eventbus.unregister(this);
                break;
            case Events.OnMessage :
                view.notify((String)event.getObject());
                //Log.d("notify", (String)event.getObject());
                break;
            case  Events.UpdateClienteOrdenSucess:
                this.UpdateClienteReferencia(); // Empieza con el orden y despues va a actualizar la referencia.
                break;
            case Events.onClienteUpdateSucess :
                view.UpdateVentas(); // Ahora que sincronize las ventas
                break;
            case Events.onVentasUpdateSucess:
                view.UpdateEncargos(); // ahora que sincronize los encargos
                break;
            case Events.onEncargoUpdateSucess:
                view.UpdateDevoluciones();
                break;
            case Events.onDevolucionesUpdateSucess:
                view.UpdateCartera();
                break;
            case Events.onUpdateCobroSucess:
                Log.d("UpdatePresenterImpl" , "onUpdateCobroSucess");
                view.setUpload();
                view.enableButtons();
                view.notify("Sincronizacion Completa.");
                CountOfflineData();
                //eventbus.unregister(this);
                break;
            case Events.onReferenceClienteUpdateSucess:
                view.UpdateCliente();
                break;
            case Events.UpdateClienteOrdenError:
                view.ShowError(event.getErrorMessage());
                view.notify(event.getErrorMessage());
                view.setUpload();
                view.enableButtons();
                CountOfflineData();
                break;
            case Events.onNetworkFails:
                view.enableButtons();
                view.ShowError(event.getErrorMessage());
                view.notify(event.getErrorMessage());
                view.setUpload();
                CountOfflineData();
                break;
        }
    }

    @Override
    public void getCLienteContador() {
        interactor.GetClienteCounter();
    }

    @Override
    public void CountOfflineData() {
        interactor.CountOfflineData();
    }

    @Override
    public void UpdateClienteReferencia() {
        //view.disableButtons();
        interactor.UpdateClienteReferencia();

    }

    @Override
    public void UpdateClienteOrden() {
        view.disableButtons();
        interactor.UpdateClienteOrden();
    }
}
