package yio.io.sifaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.fragment.NavigationDrawerFragment;
import yio.io.sifaapp.fragment.VentaListFragment;

public class BaseActivity extends AppCompatActivity  implements NavigationDrawerFragment.OnItemClickListener {

    //public BottomBar bottomBar;
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    NavigationDrawerFragment drawerFragment =null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ButterKnife.bind(this);
        setupToolbar();
        setupdrawer();

    }


    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.main);

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
                // Cerrar session
                break;
        }
        ButterKnife.unbind(this);
        drawerFragment.Close();

    }

    public BottomBar getBottomBar(){return  null;}

}
