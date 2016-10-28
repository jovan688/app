package yio.io.sifaapp.authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by STARK on 01/06/2016.
 */
public class AuthenticatorService extends Service {
    private Authenticator authenticator;

    public AuthenticatorService() {
        super();
    }

    public IBinder onBind(Intent intent) {
        IBinder ret = null;
        if (intent.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT))
            ret = getAuthenticator().getIBinder();
        return ret;
    }

    private Authenticator getAuthenticator() {
        if (authenticator == null)
            authenticator = new Authenticator(this);
        return authenticator;
    }


}
