package yio.io.sifaapp.Cartera;

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
}
