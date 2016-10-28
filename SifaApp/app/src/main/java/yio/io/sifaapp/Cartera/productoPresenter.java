package yio.io.sifaapp.Cartera;

import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 11/09/2016.
 */
public interface productoPresenter {

    void  onCreated();
    void  onDestroy();
    void  onEventMainThread(Events event);
    void getProductos();
    void getPrpductosbyCustomerId(int customerId);

}
