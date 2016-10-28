package yio.io.sifaapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;

import yio.io.sifaapp.model.Configuration;

public class sifacApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        Log.d("sifacApplication","InitApplication");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }

    public  Context getContext(){
        return getApplicationContext().getApplicationContext();
    }

    public int getUsuario (){
        Configuration configuration = new Select().from(Configuration.class).querySingle();
        if(configuration!=null)
            return configuration.getObjEmpleadoID();
        return 0;
    }

    public String getSsgCuentaID (){
        Configuration configuration = new Select().from(Configuration.class).querySingle();
        if(configuration!=null)
            return configuration.getSsgCuentaID();
        return null;
    }
}
