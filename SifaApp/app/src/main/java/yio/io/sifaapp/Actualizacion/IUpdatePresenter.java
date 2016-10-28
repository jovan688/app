package yio.io.sifaapp.Actualizacion;

import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 25/10/2016.
 */
public interface IUpdatePresenter {

    void onCreated();
    void onDestroy();
    void UpdateCartera();
    void UpdateCliente();
    void UpdateVentas();
    void UpdateDevoluciones();
    void UpdateEncargos();
    void onEventMainThread(Events event);
    void getCLienteContador();

}
