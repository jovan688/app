package yio.io.sifaapp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import yio.io.sifaapp.R;

/**
 * Created by JUANCARLOS on 06/08/2016.
 */
public class NavigationDrawerItem {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    private String Title;

    public static List<NavigationDrawerItem> getData(Context context ){
        List<NavigationDrawerItem> datalist = new ArrayList<>();
        for (String i: context.getResources().getStringArray(R.array.draweroptions)) {
            NavigationDrawerItem item = new NavigationDrawerItem();
            item.setTitle(i);
            datalist.add(item);
        }

        return  datalist;
    }

}
