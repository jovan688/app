package yio.io.sifaapp.Cartera;

/**
 * Created by JUANCARLOS on 11/09/2016.
 */
public interface productoRepository {

    void FetchData();

    void FetchDatabyCustomerId(int customerId);

    void FetchDataDetallebyCustomerId(int customerId);
}
