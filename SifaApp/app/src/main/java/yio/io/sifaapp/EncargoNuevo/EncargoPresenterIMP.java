package yio.io.sifaapp.EncargoNuevo;

import android.view.View;

import java.util.List;

import yio.io.sifaapp.model.Categoria;
import yio.io.sifaapp.model.Encargo;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 17/09/2016.
 */
public class EncargoPresenterIMP implements EncargoPresenter {

    private EventBus eventbus;
    private IEncargoView view;
    private EncargoInteractor interactor;

    public EncargoPresenterIMP(IEncargoView view) {
        this.view = view;
        this.eventbus = GreenRobotEventBus.getInstance();
        this.interactor = new EncargoInteractorIMP();
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
        switch (event.getEventype()){
            case Events.onCategoriaEncargoSucess:
                view.fetchCategorias((List<Categoria>) event.getObject());
                break;
            case Events.onFetchDataSucess:
                view.fetchProductos((List<Producto>) event.getObject());
                break;
            case Events.onFetchEncargosSucess:
                view.fetchEncargos((List<Encargo>) event.getObject());
                break;
        }
    }

    @Override
    public void GetCatergorias() {
        interactor.GetCatergorias();
    }

    @Override
    public void GetProductos() {
        interactor.GetProductos();
    }

    @Override
    public void GetProductos(int categoriaid) {
        interactor.GetProductos(categoriaid);
    }

    @Override
    public void getList() {
        interactor.getList();
    }
}
