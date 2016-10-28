package yio.io.sifaapp.Catalogos;

/**
 * Created by JUANCARLOS on 28/09/2016.
 */
public class CatalogoInteractorIMP implements  CatalogoInteractor {


    private  CatalogoRepository repository;
    public CatalogoInteractorIMP() {
        repository = new CatalogoRepositoryIMP();
    }

    @Override
    public void getRutas() {
        repository.getRutas();
    }

    @Override
    public void getCiudades() {
        repository.getCiudades();
    }

    @Override
    public void getpais() {
        repository.getpais();
    }

    @Override
    public void getGenero() {
        repository.getGenero();
    }

    @Override
    public void getCuotas() {
        repository.getCuotas();
    }

    @Override
    public void getPlazos() {
        repository.getPlazos();
    }

    @Override
    public void getDescuentos() {
        repository.fetchDescuentos();
    }
}
