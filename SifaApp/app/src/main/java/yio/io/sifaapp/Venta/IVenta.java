package yio.io.sifaapp.Venta;

import java.util.List;

import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Descuento;

/**
 * Created by JUANCARLOS on 14/08/2016.
 */
public interface IVenta {


    void onGetDescuento(List<Descuento> list);
    void onGetCuotas(List<Catalog> list);
    void onGetClientes();
    void onGetProductos();
    void onGetRutas();

}
