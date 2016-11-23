package yio.io.sifaapp.dialogos.product;

import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 15/09/2016.
 */
public interface productopresenter {

    void getAllProducts();
    void getProductsByCustomerId(long customerId);

    void onCreated();
    void onDestroy();
    void onEventMainThread(Events event);
}
