package yio.io.sifaapp.model;

import android.content.Context;
import android.content.SharedPreferences;

import yio.io.sifaapp.sifacApplication;

/**
 * Created by JUANCARLOS on 10/11/2016.
 */
public class ModelConfiguracion {

    static SharedPreferences pref;
    static SharedPreferences.Editor edit;


    public static String getURL_SERVER(Context cnt)
    {
        pref = cnt.getSharedPreferences("VConfiguracion", Context.MODE_PRIVATE);
        String url = pref.getString("url_server", "http://sifacc.azurewebsites.net/SifaccService.svc/");
       // String url = pref.getString("url_server", "http://192.168.1.10/SifacServices/SifaccService.svc/");

        return url;
    }
    public static vmConfiguracion getVMConfiguration(Context cnt)
    {
        pref = cnt.getSharedPreferences("VConfiguracion", Context.MODE_PRIVATE);
        vmConfiguracion config=vmConfiguracion.setConfiguration(
                    pref.getString("url_server", "http://sifacc.azurewebsites.net/SifaccService.svc/"),
                    //pref.getString("url_server", "http://192.168.1.10/SifacServices/SifaccService.svc/"),
                    pref.getString("device_id",String.valueOf(getDeviceID(cnt))),
                    pref.getString("enterprise", "Distribuidora Mesa")
        );
        return config;
    }

    public static String getDeviceID(Context cnt) {
        pref = cnt.getSharedPreferences("VConfiguracion", Context.MODE_PRIVATE);
        if (pref == null)
            return "0";
        return pref.getString("device_id", "0");
    }

    public static void saveConfiguration(Context view, vmConfiguracion setting)throws Exception
    {
        pref = view.getSharedPreferences("VConfiguracion", Context.MODE_PRIVATE);
        edit = pref.edit();
        edit.putString("url_server", setting.getAppServerURL());
        edit.putString("device_id", setting.getDeviceId());
        edit.putString("enterprise", setting.getEnterprise());
        edit.commit();

    }


    public static boolean removeSharedPreference(String key)
    {
        pref = sifacApplication.getContext().getSharedPreferences(key, Context.MODE_PRIVATE);
        edit = pref.edit();
        edit.clear();
        return edit.commit();
    }
}
