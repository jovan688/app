package yio.io.sifaapp.Devolucion;

import java.util.List;

import yio.io.sifaapp.model.Categoria;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Devolucion;
import yio.io.sifaapp.model.DevolucionProductos;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.model.modelSend.Venta;

/**
 * Created by JUANCARLOS on 17/09/2016.
 */
public interface IDevolucionView {


    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void onDataFetch(List<Devolucion> devolucions);
    void onCustomer(Customer c);
    void obtenerDetalle(List<DevolucionProductos> detalle);

}
