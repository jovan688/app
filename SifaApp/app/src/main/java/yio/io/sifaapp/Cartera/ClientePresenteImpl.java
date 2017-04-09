package yio.io.sifaapp.Cartera;

import android.util.Log;

import java.util.List;

import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 18/10/2016.
 */
public class ClientePresenteImpl implements IClientePresenter {

    private EventBus eventbus;
    private IDetallaCartera view;
    private IClienteInteractor interactor;


    public ClientePresenteImpl(IDetallaCartera view) {
        this.view = view;
        interactor = new ClienteInteractorImpl();
        this.eventbus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreated() {
        this.eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        this.eventbus.unregister(this);
        this.view = null;
    }

    @Override
    public void getCliente(int customerid) {
        interactor.getCliente(customerid);
    }

    @Override
    public void onEventMainThread(Events event) {
        Log.d("productoPresenterImp","onEventMainThread");
        switch (event.getEventype()){
            case Events.onFetchClienteSucess :
                setData(event);
                break;
            case Events.onSyncCLienteOrden:
                view.UpdatedOrden((Boolean)event.getObject());
                break;

            case Events.onSyncOrdenERROR:
                view.onMessage(event.getErrorMessage());
                break;
        }
    }

    @Override
    public void executeUpdateOrden(int from, int to, Cartera customer) {
        interactor.executeUpdateOrden(from,to,customer);
    }

    private void setData(Events event){
        view.onSetCliente((Customer) event.getObject());
        Log.d("CarteraListImplement","setData");
    }
}
