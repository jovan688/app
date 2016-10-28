package yio.io.sifaapp.Catalogos;

import java.util.List;

import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Ciudad;
import yio.io.sifaapp.model.Descuento;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Ruta;

/**
 * Created by JUANCARLOS on 29/09/2016.
 */
public interface ICatalogoView {

    void fetchPais(List<Pais> paises);
    void fechCiudades(List<Ciudad> ciudades);
    void fetchRutas(List<Ruta> rutas);
    void fetchGeneros(List<Catalog> generos);
    void fetchCuotas(List<Catalog> cuotas);
    void fetchPlazos(List<Catalog> plazos);
    void fetchDescuentos(List<Descuento> descuentos);

}
