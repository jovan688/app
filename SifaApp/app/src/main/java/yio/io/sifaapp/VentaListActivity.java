package yio.io.sifaapp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import yio.io.sifaapp.fragment.BlankFragment;
import yio.io.sifaapp.fragment.CarteraListFragment;
import yio.io.sifaapp.fragment.EncargoListFragment;
import yio.io.sifaapp.fragment.NuevoClienteFragment;
import yio.io.sifaapp.fragment.NuevoEncargoFragment;
import yio.io.sifaapp.fragment.NuevoVentaFragment;
import yio.io.sifaapp.fragment.SampleFragment;
import yio.io.sifaapp.fragment.VentaListFragment;

public class VentaListActivity extends BaseActivity {

    private BottomBar bottomBar;
    private  int position ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetBottomOptions(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(R.string.ventas_item));

        if (savedInstanceState != null) {
            int position = bottomBar.getCurrentTabPosition();
            if (position == 0) {
                VentaListFragment test = (VentaListFragment) getSupportFragmentManager().findFragmentByTag("VentaLista");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,test,"VentaLista").commit();
            }
            if (position == 1) {
                NuevoVentaFragment test = (NuevoVentaFragment) getSupportFragmentManager().findFragmentByTag("NuevaVenta");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,test,"NuevaVenta").commit();
            }
            if (position == 2) {
                NuevoClienteFragment test = (NuevoClienteFragment) getSupportFragmentManager().findFragmentByTag("NuevoCliente");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,test,"NuevoCliente").commit();
            }
        }
    }

    private  void SetBottomOptions(Bundle savedInstanceState) {


        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.noTabletGoodness();
        bottomBar.setItems(R.menu.ventalist_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {

                if (menuItemId == R.id.mnlist) {
                    Fragment fragment = new VentaListFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment,"VentaLista").commit();
                    getSupportActionBar().setTitle("Ventas");
                    position =0;
                }
                if (menuItemId == R.id.mnventa) {
                    Log.d("CONTACTO","NUEVO CLIENTE");
                    Fragment fragment = new NuevoVentaFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment,"NuevaVenta").commit();
                    getSupportActionBar().setTitle("Nueva Venta");
                    position = 1;

                }
                if(menuItemId == R.id.mncontact){
                    Log.d("CONTACTO","NUEVO CLIENTE");
                    Fragment fragment = new NuevoClienteFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment,"NuevoCliente").commit();
                    getSupportActionBar().setTitle("Nuevo Cliente");
                    position = 2;
                }

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.mnseach) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });
        bottomBar.selectTabAtPosition(position,true);
    }

    @Override
    public BottomBar getBottomBar(){
        return  bottomBar;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        position = savedInstanceState.getInt("position");

        //setList();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("position", position);
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        bottomBar.onSaveInstanceState(outState);
    }



}
