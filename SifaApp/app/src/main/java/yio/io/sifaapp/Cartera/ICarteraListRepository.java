package yio.io.sifaapp.Cartera;

import yio.io.sifaapp.model.Cartera;

/**
 * Created by JUANCARLOS on 25/07/2016.
 */
public interface ICarteraListRepository {

    void FetchData(int rutaid);
    void update(int fromPosition, int toPosition, Cartera cartera);

}
