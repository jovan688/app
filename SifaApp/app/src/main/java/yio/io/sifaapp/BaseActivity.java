package yio.io.sifaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.Login.ILoginView;
import yio.io.sifaapp.Login.LoginPresenter;
import yio.io.sifaapp.Login.LoginPresenterImplement;
import yio.io.sifaapp.fragment.NavigationDrawerFragment;
import yio.io.sifaapp.fragment.VentaListFragment;

public class BaseActivity extends AppCompatActivity  implements NavigationDrawerFragment.OnItemClickListener   , ILoginView {

    //public BottomBar bottomBar;
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    NavigationDrawerFragment drawerFragment =null;

    LoginPresenter loginPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // ButterKnife.bind(this);
        setContentView(R.layout.activity_base);
        setupToolbar();
        setupdrawer();

        loginPresenter = new LoginPresenterImplement(this , getApplicationContext());
        loginPresenter.onCreated();
    }


    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);
        setSupportActionBar(toolbar);

    }

    private void setupdrawer() {
        drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setListener(this);
        drawerFragment.setUpDrawer(R.id.nav_drwr_fragment, drawerLayout, toolbar);
    }

    @Override
    public void onOptionClick(Object object) {
        //Toast.makeText(this,"Desde Base Activity" + object.toString(),Toast.LENGTH_SHORT).show();
        Intent intent = null;
        switch ((int) object) {
            case 0 :
                intent = new Intent(this, CarteraListActivity.class);
                startActivity(intent);
                break;
            case 1 :
                intent = new Intent(this, VentaListActivity.class);
                startActivity(intent);
                break;
            case 2 :
                intent = new Intent(this, DevolucionListActivity.class);
                startActivity(intent);
                break;
            case 3 :
                intent = new Intent(this, EncargoListActivity.class);
                startActivity(intent);
                break;
            case 4 :
                intent = new Intent(this, ActualizarActivity.class);
                startActivity(intent);
                break;
            case 5 :
                loginPresenter.onSingOff();
                // Cerrar session
                break;
        }
        //ButterKnife.unbind(this);
        drawerFragment.Close();

    }

    public BottomBar getBottomBar(){return  null;}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void enableInputs() {

    }

    @Override
    public void disableInputs() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void authenticate() {
        Intent intent = new Intent(this, CarteraListActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleSignIn() {

    }

    @Override
    public void goToMainScreen() {

    }

    @Override
    public void loginError(String message) {

    }

    @Override
    public void Sync(String message) {

    }

    @Override
    public void onSingOff() {
        finish();
    }
}
