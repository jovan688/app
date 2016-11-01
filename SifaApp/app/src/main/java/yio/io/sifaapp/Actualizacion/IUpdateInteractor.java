package yio.io.sifaapp.Actualizacion;

/**
 * Created by JUANCARLOS on 25/10/2016.
 */
public interface IUpdateInteractor {

    void UpdateCartera();
    void UpdateCliente();
    void UpdateVentas();
    void UpdateDevoluciones();
    void UpdateEncargos();
    void GetClienteCounter();
    void CountOfflineData();
}
