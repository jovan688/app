package yio.io.sifaapp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import butterknife.ButterKnife;
import yio.io.sifaapp.fragment.CarteraListFragment;
import yio.io.sifaapp.fragment.DevolucionListFragment;
import yio.io.sifaapp.fragment.EncargoListFragment;
import yio.io.sifaapp.fragment.NuevoClienteFragment;
import yio.io.sifaapp.fragment.NuevoDevolucionFragment;
import yio.io.sifaapp.fragment.NuevoEncargoFragment;
import yio.io.sifaapp.fragment.NuevoVentaFragment;

public class EncargoListActivity extends  BaseActivity {

    private BottomBar bottomBar;
    private  int position ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetBottomOptions(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(R.string.encargos_item));
        //Init();

        if (savedInstanceState != null) {
            int position = bottomBar.getCurrentTabPosition();
            if (position == 0) {
                EncargoListFragment test = (EncargoListFragment) getSupportFragmentManager().findFragmentByTag("ListaEncargo");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,test,"ListaEncargo").commit();
            }
            if (position == 1) {
                NuevoEncargoFragment test = (NuevoEncargoFragment) getSupportFragmentManager().findFragmentByTag("NuevoEncargo");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,test,"NuevoEncargo").commit();
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
        bottomBar.setItems(R.menu.encargolist_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.mnlist) {
                    Fragment fragment = new EncargoListFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment,"ListaEncargo").commit();
                    getSupportActionBar().setTitle("Encargos");
                    position = 0;
                }

                if(menuItemId == R.id.mnencargo){
                    Fragment fragment = new NuevoEncargoFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment,"NuevoEncargo").commit();
                    position = 1;
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

    private  void Init(){


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }

}
