package yio.io.sifaapp.utils;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import yio.io.sifaapp.Actualizacion.Models.Cliente;
import yio.io.sifaapp.model.CarteraResponse;
import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Categoria;
import yio.io.sifaapp.model.Ciudad;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Descuento;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.model.Ruta;
import yio.io.sifaapp.model.authenticationRequest;
import yio.io.sifaapp.model.authenticationResponse;

/**
 * Created by Stark on 24/07/2016.
 */
public interface IServicioRemoto {

    @POST("AutenticarUsuario")
    Call<authenticationResponse> AutenticarUsuario(@Body authenticationRequest request);

    @GET("GetCarteraByCobradorId/{CobradorId}")
    Call<List<CarteraResponse>> GetCarteraByCobradorId(@Path("CobradorId") Integer CobradorId);

    @GET("GetClientesByCobradorId/{CobradorId}")
    Call<List<Customer>> GetClientesByCobradorId(@Path("CobradorId") Integer CobradorId);

    @GET("GetRutasByCobradorId/{CobradorId}")
    Call<List<Ruta>> GetRutasByCobradorId(@Path("CobradorId") Integer CobradorId);


    @GET("GetValoresCatalogos")
    Call<List<Catalog>>GetValoresCatalogos();

    @GET("GetProductos")
    Call<List<Producto>>GetProductos();

    @GET("GetDescuentos")
    Call<List<Descuento>>GetDescuentos();

    @GET("GetCategoriasProductos")
    Call<List<Categoria>>GetCategoriasProductos();

    @GET("GetCiudadesByPais/{PaisId}")
    Call<List<Ciudad>>GetCiudadesByPais(@Path("PaisId") Integer PaisID);

    @GET("GetPaisByCodigo/{PaisId}")
    Call<List<Pais>>GetPaisByCodigo(@Path("PaisId") Integer PaisID);


    @POST("CreateCliente")
    Call<Integer> CreateCliente( @Body Object entity);

}
