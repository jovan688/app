package yio.io.sifaapp.Cartera;

import android.util.Log;

import java.util.List;

import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;


/**
 * Created by JUANCARLOS on 28/07/2016.
 */
public class CarteraListPresenterImplement implements ICarteraListPresenter {

    private EventBus eventbus;
    private IDetallaCarteraView view;
    private ICarteraListInteractor CarteraInteractor;
    private int rutaId;


    public CarteraListPresenterImplement(IDetallaCarteraView view) {
        this.view = view;
        if(this.CarteraInteractor == null)
            this.CarteraInteractor = new CarteraListInteractorImplement();

        this.eventbus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreated() {
        eventbus.unregister(this);
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        eventbus.unregister(this);
        view = null;

    }

    @Override
    public void getCarteraList(int rutaid) {
        this.rutaId =rutaid;
        CarteraInteractor.FetchData(rutaid);
    }

    @Override
    public void removeCartera(Cartera cartera) {

    }

    @Override
    public void updatedCartera(int fromPosition,int toPosition  ,Cartera cartera) {
        CarteraInteractor.executeUpdateOrden(fromPosition ,toPosition ,cartera);
    }

    @Override
    public void onEventMainThread(Events event) {
        Log.d("CarteraListImplement","onEventMainThread");
        switch (event.getEventype()){
            case Events.onFetchDataSucess :
                setData(event);
                break;
            case  Events.onSyncOrden:
                getCarteraList(this.rutaId);
                break;
            case Events.onUpdateAmount:
                view.updateAmount((Float)event.getObject());
                break;
        }
    }

    @Override
    public void GetAmountCobrado(int rutaid) {
        CarteraInteractor.GetAmountCobrado(rutaid);
    }

    private void setData(Events event){
        view.setCarteraList((List<Cartera>)event.getObject());
        Log.d("CarteraListImplement","setData");
    }
}
