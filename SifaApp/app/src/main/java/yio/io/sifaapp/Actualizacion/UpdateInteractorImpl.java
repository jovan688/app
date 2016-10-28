package yio.io.sifaapp.Actualizacion;

/**
 * Created by JUANCARLOS on 25/10/2016.
 */
public class UpdateInteractorImpl implements IUpdateInteractor {

    IUpdateRepository repository;

    public UpdateInteractorImpl() {
        repository = new UpdateRepositoryImp();
    }

    @Override
    public void UpdateCartera() {
        repository.UpdateCartera();
    }

    @Override
    public void UpdateCliente() {
        repository.UpdateCliente();
    }

    @Override
    public void UpdateVentas() {
        repository.UpdateVentas();
    }

    @Override
    public void UpdateDevoluciones() {
        repository.UpdateDevoluciones();
    }

    @Override
    public void UpdateEncargos() {
        repository.UpdateEncargos();
    }

    @Override
    public void GetClienteCounter() {
        repository.GetCLienteContador();
    }
}
