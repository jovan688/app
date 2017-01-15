package yio.io.sifaapp.Login;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;


import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;


/**
 * Created by STARK on 15/06/2016.
 */
public class LoginPresenterImplement implements LoginPresenter {

    private EventBus eventbus;
    private ILoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImplement(ILoginView loginView , Context context) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImplement(context);
        this.eventbus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreated() {
        eventbus.unregister(this);
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventbus.unregister(this);
        loginInteractor = null;
    }

    @Override
    public void checkAuthenticatedUser(Context context) {
        if(loginView!=null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkSession(context);
    }

    @Override
    public void validateLogin(String username, String password) {
        if(loginView!=null){
            loginView.disableInputs();
            loginView.showProgress();
            loginView.Sync("Validando Credenciales.....");
        }
        loginInteractor.doSignIn(username,password);
    }

    @Override
    public void onEventMainThread(Events event) {
        switch (event.getEventype()){
            case Events.onSuccess :
                loginView.Sync((String)event.getObject());
                break;
            case Events.onSigInSuccess :
                onSignInSucess();
                break;
            case Events.onSigInError :
                onSignInError(event.getErrorMessage());
                break;
            case Events.onSuccessToRecoverySession:
                onSuccessToRecoverySession();
                break;
            case Events.onFailToRecoverySession :
                onFailToRecoverySession(event.getErrorMessage());
                break;
            case Events.onSyncCarteraSucess:
                onSyncCarteraSucess();
                break;
            case Events.onSystemSuccess:
                    loginView.onSystemSuccess();
                break;
            case Events.goToMainScreen:
                loginView.goToMainScreen();
                break;
            case Events.onSyncDescuentosError:
            case Events.onSyncCarteraError :
            case Events.onSyncCatalogoError:
            case Events.onSyncClientesError:
                onSyncError(event.getErrorMessage());
                break;

            case Events.onSingOff :
                loginView.onSingOff();
                break;
        }
    }

    @Override
    public void onSingOff() {
        loginInteractor.onSingOff();
    }

    @Override
    public void DownloadServer() {
        if(loginView!=null){
            loginView.disableInputs();
            loginView.showProgress();
            loginView.Sync("Conectando con el Servidor .....");
        }
        loginInteractor.DownloadServer();
    }


/*
    @Override
    public void onSync(Events type) {
        switch (type.getEventype()){
            case  Events.onSyncClientes:
                loginView.Sync(Resources.getSystem().getString(R.string.sicronizando_clientes));
                break;
            case Events.onSyncCartera:
                loginView.Sync(Resources.getSystem().getString(R.string.sicronizando_cartera));
                break;
            case Events.onSyncProductos:
                loginView.Sync(Resources.getSystem().getString(R.string.sicronizando_producto));
                break;
            case Events.onSyncCatalogo:
                loginView.Sync("Sincronizando Catalogos");
                break;
            case Events.onSyncCiudades:
                loginView.Sync(Resources.getSystem().getString(R.string.sicronizando_ciudad));
                break;
            case Events.onSyncDescuentos:
                loginView.Sync(Resources.getSystem().getString(R.string.sicronizando_descuento));
                break;
            case Events.onSyncCategoriasProductos:
                loginView.Sync(Resources.getSystem().getString(R.string.sicronizando_Categoria));
                break;
        }
    }
*/

    private void onFailToRecoverySession(String error) {
        if(loginView!=null){
            loginView.enableInputs();
            loginView.hideProgress();
            loginView.loginError(error);
        }
        Log.e("LoginPresenterImplement","onFailToRecoverySession");
    }



    private void onSignInSucess(){
        if(loginView!=null){
            loginView.hideProgress();
            loginView.showProgress();
        }
    }

    private  void onSuccessToRecoverySession(){
        if(loginView!=null){
            loginView.hideProgress();
            loginView.goToMainScreen();
        }
    }

    private void onSignInError(String error){
        if(loginView!=null){
            loginView.enableInputs();
            loginView.hideProgress();
            loginView.loginError(error);
        }
    }

    private  void onSyncError(String error){
        if(loginView!=null){
            loginView.enableInputs();
            loginView.hideProgress();
            loginView.loginError(error);
        }
    }


    private void  onSyncCarteraSucess(){
        if(loginView!=null){
            loginView.hideProgress();
            loginView.authenticate();
        }
    }

}
