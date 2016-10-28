package yio.io.sifaapp.Devolucion;

import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 19/10/2016.
 */
public interface IDevolucionListPresenter {

    void  onCreated();
    void  onDestroy();
    void  getList();
    void  getCliente(int customerid);
    void  onEventMainThread(Events event);

}
