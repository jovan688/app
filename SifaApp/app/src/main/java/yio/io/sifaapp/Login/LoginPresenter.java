package yio.io.sifaapp.Login;
import android.content.Context;

import yio.io.sifaapp.utils.Events;


/**
 * Created by STARK on 14/06/2016.
 */
public interface LoginPresenter {

    void onCreated();
    void onDestroy();
    void checkAuthenticatedUser(Context context);
    void validateLogin(String username, String password);
    void onEventMainThread(Events event);
    void onSingOff();
    void DownloadServer();

}
