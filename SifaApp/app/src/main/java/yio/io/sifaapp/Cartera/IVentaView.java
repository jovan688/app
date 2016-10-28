package yio.io.sifaapp.Cartera;

import java.util.List;

import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.modelSend.Venta;

/**
 * Created by JUANCARLOS on 19/10/2016.
 */
public interface IVentaView {
    void onDataFetch(List<Venta> ventas);
     void onCustomer(Customer c);

}
