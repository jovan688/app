package yio.io.sifaapp;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yio.io.sifaapp.Login.ILoginView;
import yio.io.sifaapp.Login.LoginPresenter;
import yio.io.sifaapp.Login.LoginPresenterImplement;
import yio.io.sifaapp.Venta.AppDialog;
import yio.io.sifaapp.utils.CustomDialog;

public class LoginActivity extends AccountAuthenticatorActivity implements ILoginView {

    @Bind(R.id.txtusername)
    EditText txtusername;
    @Bind(R.id.txtpassword)
    EditText txtpassword;
    @Bind(R.id.btnSignIn)
    Button btnSignIn;
    @Bind(R.id.textView)
    TextView textView;
    private Context mycontext;
    private static CustomDialog dlg;
    ProgressBar progressBar;
    private final String TAG = this.getClass().getSimpleName();
    public final static String PARAM_USER_PASS = "USER_PASS";
    int NOTIFICATION_DIALOG=0;
    private String mAuthTokenType;
    private AccountManager mAccountManager;
    LoginPresenter loginPresenter;

    @Override
    protected void onStop() {
        loginPresenter.onDestroy();
        super.onStop();
        Log.d("LoginActivity","onStop");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mycontext = this;
        //progressBar = new ProgressBar(getApplication());
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginPresenter = new LoginPresenterImplement(this , getApplicationContext());
        loginPresenter.onCreated();
        loginPresenter.checkAuthenticatedUser(this);

        mAccountManager = AccountManager.get(this);
    }

    @Override
    public void enableInputs() {
        SetInputs(true);
    }

    @Override
    public void disableInputs() {
        SetInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void authenticate() {
        String authtoken = String.valueOf("1234");
        String accountName = txtusername.getText().toString();

        if (mAuthTokenType == null)
            mAuthTokenType = getString(R.string.auth_type);

        Bundle data = new Bundle();
        data.putString(AccountManager.KEY_ACCOUNT_NAME, accountName);
        data.putString(AccountManager.KEY_ACCOUNT_TYPE, mAuthTokenType);
        data.putString(AccountManager.KEY_AUTHTOKEN, authtoken);
        data.putString(PARAM_USER_PASS, txtpassword.getText().toString());
        // Some extra data about the user
        Bundle userData = new Bundle();
        userData.putString("UserID", "26");
        data.putBundle(AccountManager.KEY_USERDATA, userData);

        //Make it an intent to be passed back to the Android Authenticator
        final Intent res = new Intent();
        res.putExtras(data);

        try {
            //Create the new account with Account Name and TYPE
            final Account account = new Account(accountName, mAuthTokenType);
            //Add the account to the Android System
            if (mAccountManager.addAccountExplicitly(account, txtpassword.getText().toString(), userData)) {
                // worked
                Log.d(TAG, "Account added");
                mAccountManager.setAuthToken(account, mAuthTokenType, authtoken);
                setAccountAuthenticatorResult(data);

                goToMainScreen();
            } else {
                // guess not
                Log.d(TAG, "Account NOT added");
            }
        } catch (Exception ex) {
            Log.d("HUYERROR", ex.getMessage());
        }
    }

    @OnClick(R.id.btnSignIn)
    @Override
    public void handleSignIn() {
        attemptLogin();
    }

    @Override
    public void goToMainScreen() {
        hide();
        dlg = null;
        loginPresenter.onDestroy();
        Intent intent = new Intent(this, CarteraListActivity.class);
        startActivity(intent);

    }

    @Override
    public void loginError(String message) {
        hide();
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void Sync(String message) {
        showStatus(message);
        //Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        //toast.show();
    }

    @Override
    public void onSingOff() {
        finish();
    }

    @Override
    public void onSystemSuccess() {
        hide();
        dlg = null;
        //loginPresenter.onDestroy();
        Intent intent = new Intent(this, ConfiguracionActivity.class);
        startActivity(intent);
    }

    private void SetInputs(boolean enabled) {
        btnSignIn.setEnabled(enabled);
        txtpassword.setEnabled(enabled);
        txtusername.setEnabled(enabled);
    }

    private void attemptLogin() {
        txtusername.setError(null);
        txtpassword.setError(null);

        String username = txtusername.getText().toString();
        String password = txtpassword.getText().toString();

        boolean cancel = false;
        EditText focus = null;

        if (TextUtils.isEmpty(username)) {
            txtusername.setError(getString(R.string.login_message_username_Error));
            focus = txtusername;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            txtpassword.setError(getString(R.string.login_message_password_Error));
            focus = txtpassword;
            cancel = true;
        }

        if (cancel) {
            focus.requestFocus();
        } else {
            loginPresenter.validateLogin(username, password);
            //showProgress();
        }
    }
    public void showStatus(final String mensaje, boolean... confirmacion) {

        if (confirmacion.length != 0 && confirmacion[0]) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppDialog.showMessage(mycontext, "", mensaje,
                            AppDialog.DialogType.DIALOGO_ALERTA,
                            new AppDialog.OnButtonClickListener() {
                                @Override
                                public void onButtonClick(AlertDialog _dialog,
                                                          int actionId) {

                                    if (AppDialog.OK_BUTTOM == actionId) {
                                        _dialog.dismiss();
                                    }
                                }
                            });
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dlg = new CustomDialog(mycontext, mensaje, false, NOTIFICATION_DIALOG);
                    dlg.show();
                }
            });
        }
    }

    public void hide(){
        if (dlg != null && dlg.isShowing())
            dlg.dismiss();
    }
}
