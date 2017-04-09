package yio.io.sifaapp.Cartera;

import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.utils.Events;

/**
 * Created by JUANCARLOS on 18/10/2016.
 */
public interface IClienteInteractor {


    void getCliente(int customerid);

    void executeUpdateOrden(int fromPosition, int toPosition, Cartera customer);


}
