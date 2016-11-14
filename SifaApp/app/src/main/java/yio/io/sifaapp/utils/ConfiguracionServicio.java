package yio.io.sifaapp.utils;

import android.content.Context;

import yio.io.sifaapp.model.ModelConfiguracion;
import yio.io.sifaapp.sifacApplication;

/**
 * Created by Stark on 24/07/2016.
 */
public class ConfiguracionServicio {

    private static String URL =ModelConfiguracion.getURL_SERVER(sifacApplication.getContext()); // "http://sifacc.azurewebsites.net/SifaccService.svc/";

    public static String  getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        ConfiguracionServicio.URL = URL;
    }

    public static String getURL(Context context) {
        return URL;
    }
}
