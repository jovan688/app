package yio.io.sifaapp.Catalogos;

import java.util.List;

import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Ciudad;
import yio.io.sifaapp.model.Descuento;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Ruta;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 28/09/2016.
 */
public class CatalogoPresenterIMP implements  CatalogoPresenter {

    private EventBus eventbus;
    private CatalogoInteractor interactor;
    private ICatalogoView view;

    public CatalogoPresenterIMP(ICatalogoView view) {
        eventbus =  GreenRobotEventBus.getInstance();
        interactor= new CatalogoInteractorIMP();
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
    public void getCuotas() {
        interactor.getCuotas();
    }

    @Override
    public void getPlazos() {
        interactor.getPlazos();
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
            case Events.onCuotasSucess:
                view.fetchCuotas((List<Catalog>) event.getObject());
                break;
            case Events.onPlazosSucess:
                view.fetchPlazos((List<Catalog>) event.getObject());
                break;
            case Events.onDescuentosSucess:
                view.fetchDescuentos((List<Descuento>) event.getObject());
                break;
        }
    }

    @Override
    public void fetchDescuentos() {
        interactor.getDescuentos();
    }
}
