package yio.io.sifaapp.EncargoNuevo;

/**
 * Created by JUANCARLOS on 17/09/2016.
 */
public interface EncargoInteractor {

    void GetCatergorias();
    void GetProductos();
    void GetProductos(int categoriaid);
    void getList();
}
