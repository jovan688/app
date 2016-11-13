package yio.io.sifaapp.Cartera;

import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 25/07/2016.
 */
public interface ICarteraListPresenter {

    void  onCreated();
    void  onDestroy();
    void  getCarteraList(int rutaid);
    void  removeCartera(Cartera cartera);
    void  updatedCartera(int fromPosition, int toPosition, Cartera cartera);
    void  onEventMainThread(Events event);
    void  GetAmountCobrado(int rutaid);
}
