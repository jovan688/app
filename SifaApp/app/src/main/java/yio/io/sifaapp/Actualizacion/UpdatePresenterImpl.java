package yio.io.sifaapp.Actualizacion;

import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 25/10/2016.
 */
public class UpdatePresenterImpl implements IUpdatePresenter {

    IUpdateView view;
    private EventBus eventbus;
    private IUpdateInteractor interactor;

    public UpdatePresenterImpl(IUpdateView view) {
        this.view = view;
        this.eventbus = GreenRobotEventBus.getInstance();
        this.interactor = new UpdateInteractorImpl();
    }



    @Override
    public void onCreated() {
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventbus.unregister(this);
    }


    @Override
    public void UpdateCartera() {
        interactor.UpdateCartera();
    }

    @Override
    public void UpdateCliente() {
        interactor.UpdateCliente();
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
            case Events.UpdateClienteContador :
                view.UpdateCounter(1,(int)event.getObject());
                break;
            case Events.ClienteContador:
                view.ClienteCounter((int)event.getObject());
                break;
        }
    }

    @Override
    public void getCLienteContador() {
        interactor.GetClienteCounter();
    }
}
