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
import yio.io.sifaapp.fragment.NuevoClienteFragment;
import yio.io.sifaapp.fragment.NuevoVentaFragment;
import yio.io.sifaapp.fragment.SampleFragment;
import yio.io.sifaapp.fragment.VentaListFragment;

public class VentaListActivity extends BaseActivity {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetBottomOptions(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(R.string.ventas_item));
    }

    private  void SetBottomOptions(Bundle savedInstanceState) {


        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItems(R.menu.ventalist_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.mnventa) {
                    Log.d("CONTACTO","NUEVO CLIENTE");
                    Fragment fragment = new NuevoVentaFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                    getSupportActionBar().setTitle("Nueva Venta");

                }
                if(menuItemId == R.id.mncontact){
                    Log.d("CONTACTO","NUEVO CLIENTE");
                    Fragment fragment = new NuevoClienteFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                    getSupportActionBar().setTitle("Nuevo Cliente");
                }
                if (menuItemId == R.id.mnlist) {
                    Fragment fragment = new VentaListFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                    getSupportActionBar().setTitle("Ventas");
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
