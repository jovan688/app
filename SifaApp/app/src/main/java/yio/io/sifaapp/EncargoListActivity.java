package yio.io.sifaapp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import yio.io.sifaapp.fragment.DevolucionListFragment;
import yio.io.sifaapp.fragment.EncargoListFragment;
import yio.io.sifaapp.fragment.NuevoDevolucionFragment;
import yio.io.sifaapp.fragment.NuevoEncargoFragment;

public class EncargoListActivity extends  BaseActivity {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetBottomOptions(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(R.string.encargos_item));
        //Init();
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
                    manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                    getSupportActionBar().setTitle("Encargos");
                }

                if(menuItemId == R.id.mnencargo){
                    Fragment fragment = new NuevoEncargoFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
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

    private  void Init(){


    }

}
