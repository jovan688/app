package yio.io.sifaapp.Catalogos;

import java.util.List;

import yio.io.sifaapp.model.Descuento;
import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
public interface CatalogoPresenter {

    void  onCreated();
    void  onDestroy();
    void getRutas();
    void getCiudades();
    void getpais();
    void getGenero();
    void getCuotas();
    void getPlazos();
    void onEventMainThread(Events event);
    void fetchDescuentos();

}
