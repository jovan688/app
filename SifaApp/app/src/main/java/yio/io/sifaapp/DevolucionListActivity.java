package yio.io.sifaapp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import yio.io.sifaapp.fragment.CarteraListFragment;
import yio.io.sifaapp.fragment.DevolucionListFragment;
import yio.io.sifaapp.fragment.NuevoDevolucionFragment;
import yio.io.sifaapp.fragment.NuevoEncargoFragment;
import yio.io.sifaapp.fragment.NuevoVentaFragment;
import yio.io.sifaapp.fragment.VentaListFragment;

/**
 * Created by JUANCARLOS on 01/09/2016.
 */
public class DevolucionListActivity extends  BaseActivity {

    private BottomBar bottomBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetBottomOptions(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(R.string.devolucion_item));
    }


    private  void SetBottomOptions(Bundle savedInstanceState) {


        bottomBar = BottomBar.attach(this, savedInstanceState);

        bottomBar.setItems(R.menu.devolucionlist_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId == R.id.mndevolucionadd){

                    Fragment fragment = new NuevoDevolucionFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                }
                if (menuItemId == R.id.mnlist) {
                    Fragment fragment = new DevolucionListFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                    getSupportActionBar().setTitle("Devoluciones");
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.mnseach) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });
        bottomBar.selectTabAtPosition(0,true);
    }


    @Override
    public BottomBar getBottomBar(){
        return  bottomBar;
    }

}
