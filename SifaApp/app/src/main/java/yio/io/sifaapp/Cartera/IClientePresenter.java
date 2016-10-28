package yio.io.sifaapp.Cartera;

import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 18/10/2016.
 */
public interface IClientePresenter {

    void  onCreated();
    void  onDestroy();
    void getCliente(int customerid);
    void  onEventMainThread(Events event);
}
