package yio.io.sifaapp.Cartera;

import android.util.Log;
import android.view.View;

import java.util.List;

import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.CarteraDetalle;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 11/09/2016.
 */
public class productoPresenterImp implements  productoPresenter {

    private EventBus eventbus;
    private IDetallaCartera view;
    private productoInteractor interactor;

    public productoPresenterImp(IDetallaCartera view) {
        this.view = view;
        interactor = new productoInteractorImpl();
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
    public void onEventMainThread(Events event) {
        Log.d("productoPresenterImp","onEventMainThread");
        switch (event.getEventype()){
            case Events.onFetchDataSucess :
                setData(event);
                break;
        }
    }

    @Override
    public void getProductos( ) {
        interactor.getProductos();
    }

    @Override
    public void getPrpductosbyCustomerId(int customerId) {
        interactor.getPrpductosbyCustomerId(customerId);
    }

    private void setData(Events event){
        view.onSetCarteraDetalle((List<Producto>) event.getObject());
        Log.d("CarteraListImplement","setData");
    }
}
