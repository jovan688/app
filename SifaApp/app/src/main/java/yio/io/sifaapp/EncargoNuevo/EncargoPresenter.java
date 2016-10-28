package yio.io.sifaapp.EncargoNuevo;

import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 17/09/2016.
 */
public interface EncargoPresenter {

    void onCreated();
    void onDestroy();
    void onEventMainThread(Events event);
    void GetCatergorias();
    void GetProductos();
    void GetProductos(int categoriaid);
    void  getList();
}
