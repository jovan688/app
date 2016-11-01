package yio.io.sifaapp.Devolucion;

/**
 * Created by JUANCARLOS on 23/10/2016.
 */
public class IDevolucionListInteractorImpl implements IDevolucionListInteractor {


    IDevolucionRepository repository;

    public IDevolucionListInteractorImpl() {
        repository = new IDevolucionRepositoryImpl();
    }

    @Override
    public void getList() {
        repository.getList();
    }

    @Override
    public void getCliente(int customerid) {
        repository.getCliente(customerid);
    }

    @Override
    public void obtenerDetalle(int customerid) {
        repository.obtenerDetalle(customerid);
    }
}
