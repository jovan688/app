package yio.io.sifaapp.Cartera;

import yio.io.sifaapp.Venta.IVentaInteractor;
import yio.io.sifaapp.Venta.IVentaRepository;

/**
 * Created by JUANCARLOS on 19/10/2016.
 */
public class IventaListInteractorImpl implements IVentaListInteractor{

    IVentaListRepository repository = null;


    public IventaListInteractorImpl() {
        repository = new IVentaListRepositoryImpl();
    }

    @Override
    public void getList() {
        repository.getList();
    }

    @Override
    public void getCliente(int customerid) {
        repository.getCliente(customerid);
    }


}
