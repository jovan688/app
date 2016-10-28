package yio.io.sifaapp.Catalogos;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Ciudad;
import yio.io.sifaapp.model.Descuento;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Ruta;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 28/09/2016.
 */
public class CatalogoRepositoryIMP implements  CatalogoRepository {


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

    @Override
    public void getCuotas() {
        List<Catalog> lista = new Select().from(Catalog.class).where(String.format("Nombre = 'MODALIDADPAGO'")).queryList();
        postEvent(Events.onCuotasSucess,lista);
    }

    @Override
    public void getPlazos() {
        List<Catalog> plazos = new Select().from(Catalog.class).where(String.format("Nombre='PLAZOS'")).queryList();
        postEvent(Events.onPlazosSucess,plazos);
    }

    @Override
    public void fetchDescuentos() {

        List<Descuento> descuentos = new Select().from(Descuento.class).queryList();
        postEvent(Events.onDescuentosSucess,descuentos);
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
