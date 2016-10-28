package yio.io.sifaapp.ClienteNuevo;

import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
public interface ClientePresenter {

    void onCreated();
    void onDestroy();
    void getRutas();
    void getCiudades();
    void getpais();
    void getGenero();
    void onEventMainThread(Events event);
}
