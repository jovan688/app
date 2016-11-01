package yio.io.sifaapp.Devolucion;

import android.util.Log;

import java.util.List;

import yio.io.sifaapp.Cartera.IVentaListInteractor;
import yio.io.sifaapp.Cartera.IVentaView;
import yio.io.sifaapp.Cartera.IventaListInteractorImpl;
import yio.io.sifaapp.model.Devolucion;
import yio.io.sifaapp.model.DevolucionProductos;
import yio.io.sifaapp.model.modelSend.Venta;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 23/10/2016.
 */
public class IDevolucionListPresenterImpl implements  IDevolucionListPresenter{

    private EventBus eventbus;
    IDevolucionListInteractor interactor;
    IDevolucionView view;


    public IDevolucionListPresenterImpl(IDevolucionView view) {
        this.view = view;
        interactor = new IDevolucionListInteractorImpl();
        this.eventbus = GreenRobotEventBus.getInstance();
    }


    @Override
    public void onCreated() {
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        eventbus.unregister(this);
        view = null;
    }

    @Override
    public void getList() {
            interactor.getList();
    }

    @Override
    public void getCliente(int customerid) {
        interactor.getCliente(customerid);
    }

    @Override
    public void obtenerDetalle(int customerid) {
        interactor.obtenerDetalle(customerid);
    }

    @Override
    public void onEventMainThread(Events event) {
        Log.d("CarteraListImplement","onEventMainThread");
        switch (event.getEventype()) {
            case Events.onFetchDevolucionSucess:
                setData(event);
                break;
            case Events.onCarteraDetalleDataSucess:
                view.obtenerDetalle((List<DevolucionProductos> )event.getObject());
                break;
        }
    }
    private void setData(Events event){
        view.onDataFetch((List<Devolucion>)event.getObject());
    }
}
