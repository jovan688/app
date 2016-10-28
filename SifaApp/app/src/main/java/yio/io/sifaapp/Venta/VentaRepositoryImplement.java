package yio.io.sifaapp.Venta;

import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Descuento;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 28/08/2016.
 */
public class VentaRepositoryImplement implements IVentaRepository {


    public VentaRepositoryImplement(){}


    @Override
    public void getDescuento() {
        List<Descuento> list = new Select().from(Descuento.class).queryList();

        postEvent(Events.onFetchDataSucess,list);
    }

    @Override
    public void getNumeroCuotas() {
        List<Catalog> list = new Select().from(Catalog.class).where(Condition.column("Nombre").eq("PLAZOS")).queryList();

        postEvent(Events.onFetchDataSucess,list);
    }

    private void postEvent(int type, Object obj) {
        Events event = new Events();
        event.setEventype(type);
        event.setObject(obj);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }
}
