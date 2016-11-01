package yio.io.sifaapp.Actualizacion;

import yio.io.sifaapp.model.ContadorModel;
import yio.io.sifaapp.utils.TypeCounter;

/**
 * Created by JUANCARLOS on 25/10/2016.
 */
public interface IUpdateView {

    void enableButtons();
    void disableButtons();
    void showProgress();
    void hideProgress();
    void CountOfflineData(ContadorModel contadores);
    void UpdateCounter(TypeCounter type , int count);
    void ClienteCounter(int count);
    void notify(String message);
    void UpdateVentas();
    void UpdateEncargos();
    void UpdateDevoluciones();
    void UpdateCartera();
    void ShowError(String message);
}
