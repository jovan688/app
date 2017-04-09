package yio.io.sifaapp.Cartera;

import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Customer;

/**
 * Created by JUANCARLOS on 18/10/2016.
 */
public class ClienteInteractorImpl implements IClienteInteractor {

    private IClienteRepository repository;

    public ClienteInteractorImpl() {
        repository = new ClienteRepositoryImpl();
    }

    @Override
    public void getCliente(int customerid) {
        repository.getCliente(customerid);
    }

    @Override
    public void executeUpdateOrden(int fromPosition, int toPosition, Cartera customer) {
            repository.executeUpdateOrden(fromPosition,toPosition,customer);
    }
}
