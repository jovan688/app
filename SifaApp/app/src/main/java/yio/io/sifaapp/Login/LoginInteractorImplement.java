package yio.io.sifaapp.Login;

import android.content.Context;




/**
 * Created by STARK on 15/06/2016.
 */
public class LoginInteractorImplement implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImplement(Context context) {
        this.loginRepository =  new LoginRepositoryImplement(context);
    }

    @Override
    public void checkSession(Context context) {
        loginRepository.checkSession(context);
    }

    @Override
    public void doSignIn(String username, String password) {
        loginRepository.signIn(username,password);
    }
}
