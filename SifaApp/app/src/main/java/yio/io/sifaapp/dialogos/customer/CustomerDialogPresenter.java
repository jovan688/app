package yio.io.sifaapp.dialogos.customer;

import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 15/09/2016.
 */
public interface CustomerDialogPresenter {

    void onFetchData(int rutaid);
    void onFetchData();
    void onCreated();
    void onDestroy();
    void onEventMainThread(Events event);
}
