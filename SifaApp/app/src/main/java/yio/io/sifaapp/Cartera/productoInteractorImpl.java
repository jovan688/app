package yio.io.sifaapp.Cartera;

import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 11/09/2016.
 */
public class productoInteractorImpl implements  productoInteractor {

    private productoRepository repository;

    public productoInteractorImpl() {
        repository = new productoRepositoryImpl();
    }

    @Override
    public void getProductos() {
        repository.FetchData();
    }

    @Override
    public void getPrpductosbyCustomerId(int customerId) {
        repository.FetchDataDetallebyCustomerId(customerId);
    }
}
