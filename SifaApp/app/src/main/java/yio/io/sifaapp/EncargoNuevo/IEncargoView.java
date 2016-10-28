package yio.io.sifaapp.EncargoNuevo;

import java.util.List;

import yio.io.sifaapp.model.Categoria;
import yio.io.sifaapp.model.Encargo;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Producto;

/**
 * Created by JUANCARLOS on 17/09/2016.
 */
public interface IEncargoView {


    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void fetchCategorias(List<Categoria> categorias);
    void fetchProductos(List<Producto> productos);
    void fetchEncargos(List<Encargo> encargos);



}
