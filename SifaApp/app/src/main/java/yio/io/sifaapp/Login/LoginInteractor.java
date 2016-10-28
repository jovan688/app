package yio.io.sifaapp.Login;

import android.content.Context;
/**
 * Created by STARK on 14/06/2016.
 */
public interface LoginInteractor {

    void checkSession(Context context);

    void doSignIn(String username, String password);

}
