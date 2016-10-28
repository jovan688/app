package yio.io.sifaapp.ClienteNuevo;

import java.util.List;

import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Ciudad;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Ruta;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
public interface IClienteView {

    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();
    void fetchPais(List<Pais> paises);
    void fechCiudades(List<Ciudad> ciudades);
    void fetchRutas(List<Ruta> rutas);
    void fetchGeneros(List<Catalog> generos);
}
