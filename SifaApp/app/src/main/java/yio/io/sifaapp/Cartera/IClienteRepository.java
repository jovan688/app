package yio.io.sifaapp.Cartera;

import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Customer;

/**
 * Created by JUANCARLOS on 18/10/2016.
 */
public interface IClienteRepository {
    void getCliente(int customerid);

    void executeUpdateOrden(int fromPosition, int toPosition, Cartera customer);
}