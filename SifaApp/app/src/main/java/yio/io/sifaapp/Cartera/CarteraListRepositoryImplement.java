package yio.io.sifaapp.Cartera;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Update;

import java.util.ArrayList;
import java.util.List;

import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Cobro;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;


/**
 * Created by JUANCARLOS on 28/07/2016.
 */
public class CarteraListRepositoryImplement implements ICarteraListRepository {

    private final String TAG = this.getClass().getSimpleName();
    public CarteraListRepositoryImplement() {

    }

    @Override
    public void FetchData(int rutaid) {
        Log.d("Implement", "FetchData");
        List<Cartera> list = null;

        list = new Select().from(Cartera.class).where(String.format("StbRutaID=%d",rutaid)).orderBy("OrdenCobro").queryList();
        postEvent(Events.onFetchDataSucess, list);
    }

    @Override
    public void update(int fromPosition,int toPosition  ,Cartera cartera) {
        try {
           // List<Cartera> list;
            if (fromPosition > toPosition) {
                Log.d(TAG, String.format("OrdenCobro  >=%d And OrdenCobro < %d", toPosition, fromPosition));

                Update.table(Cartera.class)
                        .set("OrdenCobro=OrdenCobro+1")
                        .where(String.format("OrdenCobro  >=%d And OrdenCobro < %d and StbRutaID = %d", toPosition, fromPosition, cartera.getStbRutaID()))
                        .async()
                        .execute();
            /*
            list = new Select().from(Cartera.class).where(String.format("OrdenCobro  >=%d And OrdenCobro < %d and StbRutaID = %d", toPosition, fromPosition,cartera.getStbRutaID())).queryList();
            for (Cartera i : list) {
                i.setOrdenCobro(i.getOrdenCobro()+ 1 );
                i.save();
            }
            */
            } else {
                // fromposition < topposition
                Log.d(TAG, String.format("OrdenCobro  >=%d And OrdenCobro < %d", toPosition, fromPosition));
                //list = new Select().from(Cartera.class).where(String.format("OrdenCobro  >=%d And OrdenCobro < %d", fromPosition , toPosition)).queryList();
                Update.table(Cartera.class)
                        .set("OrdenCobro= OrdenCobro-1")
                        .where(String.format("OrdenCobro  >%d And OrdenCobro <= %d and StbRutaID = %d", fromPosition, toPosition, cartera.getStbRutaID()))
                        .async()
                        .execute();
            /*
            list = new Select().from(Cartera.class).where(String.format("OrdenCobro  >%d And OrdenCobro <= %d and StbRutaID = %d", fromPosition ,toPosition , cartera.getStbRutaID())).queryList();
            for (Cartera i : list) {
                i.setOrdenCobro(i.getOrdenCobro()- 1 );
                i.save();
            }*/
            }
            cartera.save();
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        }
        postEvent(Events.onSyncOrden, null);
    }

    @Override
    public void GetAmountCobrado(int rutaid) {

        Float amount = new Float(0);
        List<Cobro>  list = new Select().from(Cobro.class).where(String.format("objStbRutaID=%d",rutaid)).queryList();
        for (Cobro item: list) {
            amount += item.getMontoAbonado();
        }
        postEvent(Events.onUpdateAmount, amount);
    }


    private void postEvent(int type, Object obj) {
        Events event = new Events();
        event.setEventype(type);
        event.setObject(obj);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }

    private void postEvent(int type, String errorMessage) {
        Events event = new Events();
        event.setEventype(type);
        if (errorMessage != null) {
            event.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }
}
