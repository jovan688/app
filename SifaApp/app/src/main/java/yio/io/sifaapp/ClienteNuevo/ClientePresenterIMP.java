package yio.io.sifaapp.ClienteNuevo;

import java.util.List;

import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Ciudad;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Ruta;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
public class ClientePresenterIMP implements  ClientePresenter {

    private ClienteInteractor interactor;
    private EventBus eventbus;
    private IClienteView view;

    public ClientePresenterIMP(IClienteView view) {
        this.eventbus = GreenRobotEventBus.getInstance();
        interactor = new ClienteInteractorIMP();
        this.view = view;
    }



    @Override
    public void onCreated() {
        eventbus.unregister(this);
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        eventbus.unregister(this);
        this.view = null;
    }

    @Override
    public void getRutas() {
        interactor.getRutas();
    }

    @Override
    public void getCiudades() {
        interactor.getCiudades();
    }

    @Override
    public void getpais() {
        interactor.getpais();
    }

    @Override
    public void getGenero() {
        interactor.getGenero();
    }

    @Override
    public void onEventMainThread(Events event) {
        switch (event.getEventype()){
            case Events.onCiudadesSucess:
                view.fechCiudades((List<Ciudad>) event.getObject());
            break;
            case Events.onRutaSucess:
                view.fetchRutas((List<Ruta>) event.getObject());
                break;
            case Events.onGenerosSucess:
                view.fetchGeneros((List<Catalog>) event.getObject());
                break;
            case Events.onPaisesSucess :
                view.fetchPais((List<Pais>) event.getObject());
                break;
        }
    }
}
