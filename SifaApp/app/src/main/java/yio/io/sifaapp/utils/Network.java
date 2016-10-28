package yio.io.sifaapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by JUANCARLOS on 07/08/2016.
 */
public class Network {

    public static ResponseMessage isPhoneConnected(Context context){
        ResponseMessage message=null;
        try
        {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if(info== null)
            {
                message=new ResponseMessage("Error de conexión",true," Dispositivo Fuera de linea","\n Causa: No hay ninguna conexion activa");
            }
            else if(!info.isConnected())
            {
                message=new ResponseMessage("Error de conexión",true," Dispositivo Fuera de linea","\n Causa: El dispositivo no esta conectado a ninguna conexión activa");
            }



        }catch (Exception e) {
            e.printStackTrace();
            message.setHasError(true);
            message.setCause(e.getMessage());
            message.setTittle("Error");
            message.setTittle("Intentelo nuevamente...");
        }
        return message;
    }
}
