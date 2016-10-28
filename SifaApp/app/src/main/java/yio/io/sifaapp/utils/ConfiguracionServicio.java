package yio.io.sifaapp.utils;

/**
 * Created by Stark on 24/07/2016.
 */
public class ConfiguracionServicio {

    private static String URL = "http://sifacc.azurewebsites.net/SifaccService.svc/";

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        ConfiguracionServicio.URL = URL;
    }


}
