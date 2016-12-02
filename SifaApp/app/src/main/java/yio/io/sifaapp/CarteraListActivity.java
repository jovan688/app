package yio.io.sifaapp;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import butterknife.ButterKnife;
import yio.io.sifaapp.fragment.CarteraListFragment;
import yio.io.sifaapp.fragment.DetalleClienteFragment;
import yio.io.sifaapp.fragment.NuevoClienteFragment;
import yio.io.sifaapp.fragment.NuevoVentaFragment;

public class CarteraListActivity extends BaseActivity {

    private BottomBar bottomBar;
    private  int position ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetBottomOptions(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(R.string.cartera_item));

        if (savedInstanceState != null) {
            DetalleClienteFragment detalle = null;

            detalle =  (DetalleClienteFragment) getSupportFragmentManager().findFragmentByTag("Detalle");

            if(detalle != null)

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,detalle,"Detalle").commit();
            else
            {
                int position = bottomBar.getCurrentTabPosition();
                if (position == 0) {
                    CarteraListFragment test = (CarteraListFragment) getSupportFragmentManager().findFragmentByTag("ListaCartera");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, test, "ListaCartera").commit();
                }
                if (position == 1) {
                    NuevoClienteFragment test = (NuevoClienteFragment) getSupportFragmentManager().findFragmentByTag("NuevoCliente");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, test, "NuevoCliente").commit();
                }
                if (position == 2) {
                    NuevoVentaFragment test = (NuevoVentaFragment) getSupportFragmentManager().findFragmentByTag("NuevoCartera");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, test, "NuevoCartera").commit();
                }
            }
        }


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


    private  void SetBottomOptions(Bundle savedInstanceState) {


        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.noTabletGoodness();
        bottomBar.setItems(R.menu.carteralist_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.mnlist) {
                    Fragment fragment = new CarteraListFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment,"ListaCartera").commit();
                    position = 0;
                }
                if(menuItemId == R.id.mncontact){
                    Fragment fragment = new NuevoClienteFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment, "NuevoCliente").commit();
                    getSupportActionBar().setTitle("Nuevo Cliente");
                    position = 1;
                }
                if(menuItemId == R.id.mncartera){
                    Fragment fragment = new NuevoVentaFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment,"NuevoCartera").commit();
                    getSupportActionBar().setTitle("Nuevo Venta");
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
        // Disable the left bar on tablets and behave exactly the same on mobile and tablets instead.

    }
    @Override
    public BottomBar getBottomBar(){
        return  bottomBar;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
