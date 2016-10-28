package yio.io.sifaapp.Cartera;

import java.util.List;

import yio.io.sifaapp.model.CarteraDetalle;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Producto;

/**
 * Created by JUANCARLOS on 11/09/2016.
 */
public interface IDetallaCartera {

    void  onSetCarteraDetalle(List<Producto> detalles);
    void  onSetCliente(Customer customer);

}
