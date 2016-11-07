package yio.io.sifaapp.Login;

import android.content.Context;

/**
 * Created by STARK on 14/06/2016.
 */
public interface LoginRepository {

    void   signIn(String username, String password);

    void   signOut();

    void  checkSession(Context context);

    void  GetProductos();

    void  GetCatalogos();

    void  GetClientesByCobradorId();

    void  GetDescuentos();

    void GetCategoriasProductos();

    void GetCiudades();

    void GetCarteraByCobradorId();

    void GetRutasByCobradorId();

    void DownloadServer();
}
