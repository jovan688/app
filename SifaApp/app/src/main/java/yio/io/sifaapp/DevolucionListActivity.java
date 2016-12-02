package yio.io.sifaapp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import yio.io.sifaapp.fragment.CarteraListFragment;
import yio.io.sifaapp.fragment.DevolucionListFragment;
import yio.io.sifaapp.fragment.NuevoClienteFragment;
import yio.io.sifaapp.fragment.NuevoDevolucionFragment;
import yio.io.sifaapp.fragment.NuevoEncargoFragment;
import yio.io.sifaapp.fragment.NuevoVentaFragment;
import yio.io.sifaapp.fragment.VentaListFragment;

/**
 * Created by JUANCARLOS on 01/09/2016.
 */
public class DevolucionListActivity extends  BaseActivity {

    private BottomBar bottomBar = null;
    private  int position ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetBottomOptions(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(R.string.devolucion_item));

        if (savedInstanceState != null) {
            int position = bottomBar.getCurrentTabPosition();
            if (position == 1) {
                NuevoDevolucionFragment test = (NuevoDevolucionFragment) getSupportFragmentManager().findFragmentByTag("NuevaDevolucion");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,test,"NuevaDevolucion").commit();
            }
            if (position == 0) {
                DevolucionListFragment test = (DevolucionListFragment) getSupportFragmentManager().findFragmentByTag("DevolucionLista");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,test,"DevolucionLista").commit();
            }

        }
    }


    private  void SetBottomOptions(Bundle savedInstanceState) {


        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.noTabletGoodness();
        bottomBar.setItems(R.menu.devolucionlist_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId == R.id.mndevolucionadd){

                    Fragment fragment = new NuevoDevolucionFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment,"NuevaDevolucion").commit();
                    position = 1;
                }
                if (menuItemId == R.id.mnlist) {
                    Fragment fragment = new DevolucionListFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment,"DevolucionLista").commit();
                    getSupportActionBar().setTitle("Devoluciones");
                    position = 0;
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
