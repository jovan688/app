package yio.io.sifaapp.Cartera;


import yio.io.sifaapp.model.Cartera;

/**
 * Created by JUANCARLOS on 28/07/2016.
 */
public class CarteraListInteractorImplement implements  ICarteraListInteractor{

    private ICarteraListRepository repository;

    public CarteraListInteractorImplement() {
        repository = new CarteraListRepositoryImplement();
    }

    @Override
    public void FetchData(int rutaid) {
        repository.FetchData(rutaid);
    }

    @Override
    public void executeUpdateOrden(int fromPosition,int toPosition  ,Cartera cartera) {
        repository.update(fromPosition, toPosition ,cartera);
    }

    @Override
    public void executeDelete(Cartera cartera) {

    }

    @Override
    public void GetAmountCobrado(int rutaid) {
        repository.GetAmountCobrado(rutaid);
    }
}
