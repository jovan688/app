package yio.io.sifaapp.Venta;

/**
 * Created by JUANCARLOS on 28/08/2016.
 */
public class VentaPresenterImplemt implements  IVentaPresenter {

    IVenta view;
    private IVentaInteractor iVentaInteractor;

    public VentaPresenterImplemt(IVenta view) {
        this.view = view;
        iVentaInteractor = new VentaInteractorImplement();

    }

    @Override
    public void onGetDescuento() {
        iVentaInteractor.getValues();
    }

    @Override
    public void onGetCuotas() {

    }

    @Override
    public void onGetClientes() {

    }

    @Override
    public void onGetProductos() {

    }

    @Override
    public void onGetRutas() {

    }
}
