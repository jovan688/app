package yio.io.sifaapp.Actualizacion;

/**
 * Created by JUANCARLOS on 25/10/2016.
 */
public interface IUpdateView {

    void enableButtons();
    void disableButtons();
    void showProgress();
    void hideProgress();
    void UpdateCartera();
    void UpdateCliente();
    void UpdateVentas();
    void UpdateDevoluciones();
    void UpdateEncargos();
    void UpdateError(String message);
    void Sync(String message);
    void UpdateCounter(int type , int count);
    void ClienteCounter(int count);

}
