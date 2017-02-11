package yio.io.sifaapp.EncargoNuevo;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import yio.io.sifaapp.model.Categoria;
import yio.io.sifaapp.model.Encargo;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.model.Ruta;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 17/09/2016.
 */
public class EncargoRepositoryIMP implements  EncargoRepository {

    public EncargoRepositoryIMP() {
    }

    @Override
    public void GetCatergorias() {
        List<Categoria> lista = new Select().from(Categoria.class).queryList();
        postEvent(Events.onCategoriaEncargoSucess,lista);
    }

    @Override
    public void GetProductos() {
        List<Producto> lista = new Select().from(Producto.class).where(String.format("Activo=1")).queryList();
        postEvent(Events.onFetchDataSucess,lista);
    }

    @Override
    public void GetProductos(int categoriaId) {
        List<Producto> lista = new Select().from(Producto.class).where(String.format("objCategoriaID=%d",categoriaId)).queryList();
        postEvent(Events.onFetchDataSucess,lista);
    }

    @Override
    public void GetList() {
        List<Encargo> lista = new  Select().from(Encargo.class).queryList();
        postEvent(Events.onFetchEncargosSucess,lista);
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
