package yio.io.sifaapp.Cartera;


import yio.io.sifaapp.model.Cartera;

/**
 * Created by JUANCARLOS on 26/07/2016.
 */
public interface ICarteraListInteractor {

    void FetchData(int rutaid);
    void executeUpdateOrden(int fromPosition, int toPosition, Cartera cartera);
    void executeDelete(Cartera cartera);
    void GetAmountCobrado(int rutaid);
}
