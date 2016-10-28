package yio.io.sifaapp.dialogos.customer;

/**
 * Created by JUANCARLOS on 15/09/2016.
 */
public class CustomerDialogInteractorImpl implements CustomerDialogInteractor {


    private CustomerDialogRepository repository;

    public CustomerDialogInteractorImpl() {
        repository = new CustomerDialogRepositoryImp() ;
    }

    @Override
    public void onFetchData() {
        repository.OnFetchData();
    }

    @Override
    public void onFetchData(int rutaid) {
        repository.OnFetchData(rutaid);
    }
}
