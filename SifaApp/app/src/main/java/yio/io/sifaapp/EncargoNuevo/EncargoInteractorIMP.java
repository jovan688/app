package yio.io.sifaapp.EncargoNuevo;

/**
 * Created by JUANCARLOS on 17/09/2016.
 */
public class EncargoInteractorIMP implements  EncargoInteractor {

    EncargoRepository repository;

    public EncargoInteractorIMP() {
        repository = new EncargoRepositoryIMP();
    }

    @Override
    public void GetCatergorias() {
        repository.GetCatergorias();
    }

    @Override
    public void GetProductos() {
        repository.GetProductos();
    }

    @Override
    public void GetProductos(int categoriaid) {
        repository.GetProductos(categoriaid);
    }

    @Override
    public void getList() {
        repository.GetList();
    }
}
