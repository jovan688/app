package yio.io.sifaapp.Cartera;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;


/**
 * Created by JUANCARLOS on 28/07/2016.
 */
public class CarteraListRepositoryImplement implements ICarteraListRepository {


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

        List<Cartera>  list ;
        if(fromPosition > toPosition) {
            list = new Select().from(Cartera.class).where(String.format("OrdenCobro  >=%d And OrdenCobro < %d", toPosition, fromPosition)).queryList();
            for (Cartera i : list) {
                i.setOrdenCobro(i.getOrdenCobro()+ 1 );
                i.save();
            }
        }
        else {
            // fromposition < topposition
            list = new Select().from(Cartera.class).where(String.format("OrdenCobro  >=%d And OrdenCobro < %d", fromPosition , toPosition)).queryList();
            for (Cartera i : list) {
                i.setOrdenCobro(i.getOrdenCobro()- 1 );
                i.save();
            }
        }
        cartera.save();
        postEvent(Events.onSyncOrden,null);
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
