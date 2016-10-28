package yio.io.sifaapp.ClienteNuevo;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
public class ClienteInteractorIMP implements  ClienteInteractor {

    ClienteRepository repository;
    public ClienteInteractorIMP() {
        repository = new ClienteRepositoryIMP();
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
}
