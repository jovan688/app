package yio.io.sifaapp.dialogos.customer;

import java.util.List;

import yio.io.sifaapp.dialogos.IDialogView;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 15/09/2016.
 */
public class CustomerDialogPresenterImp implements CustomerDialogPresenter {
    private EventBus eventbus;
    private IDialogView devolucionView;
    private CustomerDialogInteractor interactor;

    public CustomerDialogPresenterImp(IDialogView view) {
        this.devolucionView = view;
        this.interactor = new CustomerDialogInteractorImpl();
        this.eventbus = GreenRobotEventBus.getInstance();

    }

    @Override
    public void onFetchData(int rutaid) {
        interactor.onFetchData(rutaid);
    }

    @Override
    public void onFetchData() {
        interactor.onFetchData();
    }

    @Override
    public void onCreated() {
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        devolucionView = null;
        eventbus.unregister(this);
    }

    @Override
    public void onEventMainThread(Events event) {
        switch (event.getEventype()){
            case Events.onFetchCustomerSucess :
                devolucionView.onFetchData( (List<Object>)event.getObject());
                break;
        }
    }
}
