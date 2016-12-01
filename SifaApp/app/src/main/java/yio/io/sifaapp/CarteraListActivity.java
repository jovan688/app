package yio.io.sifaapp;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import butterknife.ButterKnife;
import yio.io.sifaapp.fragment.CarteraListFragment;
import yio.io.sifaapp.fragment.NuevoClienteFragment;
import yio.io.sifaapp.fragment.NuevoVentaFragment;

public class CarteraListActivity extends BaseActivity {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetBottomOptions(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(R.string.cartera_item));

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
                    manager.beginTransaction().add(R.id.fragment_container,fragment).commit();
                }
                if(menuItemId == R.id.mncontact){
                    Fragment fragment = new NuevoClienteFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                    getSupportActionBar().setTitle("Nuevo Cliente");
                }
                if(menuItemId == R.id.mncartera){
                    Fragment fragment = new NuevoVentaFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                    getSupportActionBar().setTitle("Nuevo Venta");
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
