package yio.io.sifaapp.Cartera;

import java.util.List;

import yio.io.sifaapp.model.Cartera;

/**
 * Created by JUANCARLOS on 25/07/2016.
 */
public interface IDetallaCarteraView {

    void setCarteraList(List<Cartera> data);

    void carteraupdated(int fromPosition, int toPosition, Cartera cartera);

    void  carteraDeleted(Cartera cartera);





}
