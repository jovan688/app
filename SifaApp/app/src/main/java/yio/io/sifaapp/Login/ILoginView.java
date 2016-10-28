package yio.io.sifaapp.Login;

/**
 * Created by JUANCARLOS on 24/07/2016.
 */
public interface ILoginView {

    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();
    void authenticate();
    void handleSignIn();
    void goToMainScreen();
    void loginError(String message);
    void Sync(String message);
}
