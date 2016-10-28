package yio.io.sifaapp.ClienteNuevo;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Ciudad;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Ruta;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
public class ClienteRepositoryIMP implements  ClienteRepository {


    @Override
    public void getRutas() {
        List<Ruta> lista = new Select().from(Ruta.class).queryList();
        postEvent(Events.onRutaSucess,lista);
    }

    @Override
    public void getCiudades() {
        List<Ciudad> list = new Select().from(Ciudad.class).queryList();
        postEvent(Events.onCiudadesSucess,list);
    }

    @Override
    public void getpais() {
        List<Pais> list = new Select().from(Pais.class).queryList();
        postEvent(Events.onPaisesSucess,list);
    }

    @Override
    public void getGenero() {
        List<Catalog> generos = new Select().from(Catalog.class).where(String.format("Nombre='GENERO'")).queryList();
        postEvent(Events.onGenerosSucess,generos);
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
