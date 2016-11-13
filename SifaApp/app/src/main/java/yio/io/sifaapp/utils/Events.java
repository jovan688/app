package yio.io.sifaapp.utils;

import java.util.Objects;

/**
 * Created by STARK on 15/06/2016.
 */
public class Events {

    public  final static int onSigInError =0;
    public  final static int onSigInSuccess =1;
    public  final static int onSuccessToRecoverySession =2;
    public  final static int onFailToRecoverySession =3;
    public  final static int onFetchDataSucess =4;
    public  final static int onSonFetchDataError =5;

    public  final static int onSyncClientes =6;
    public  final static int onSyncClientesSucess =7;
    public  final static int onSyncClientesError =8;

    public  final static int onSyncCartera = 9;
    public  final static int onSyncCarteraSucess = 10;
    public  final static int onSyncCarteraError = 11;

    public  final static int onSyncCatalogo = 12;
    public  final static int onSyncCatalogoSucess = 13;
    public  final static int onSyncCatalogoError = 14;

    public  final static int onSyncProductos = 15;
    public  final static int onSyncProductosSucess = 16;
    public  final static int onSyncProductosError = 17;

    public  final static int onSyncDescuentos = 18;
    public  final static int onSyncDescuentosSucess = 19;
    public  final static int onSyncDescuentosError = 20;

    public  final static int onSyncCiudades = 21;
    public  final static int onSyncCiudadesSucess = 22;
    public  final static int onSyncCiudadesError = 23;

    public  final static int onSyncCategoriasProductos = 25;
    public  final static int onSyncCategoriasProductosSucess = 26;
    public  final static int onSyncCategoriasProductosError = 27;

    public final static int onSyncRuta = 28;
    public final static int onSyncRutaSucess=29;
    public final static int onSyncRutaError=30;

    public final static int onSyncOrden=31;
    public final static int onSuccess = 32;

    public final static int onCiudadesSucess=33;
    public final static int onRutaSucess=34;

    public final static  int onGenerosSucess = 35;

    public  final static int onSyncPaises = 36;
    public  final static int onSyncPaisesSucess = 37;
    public  final static int onSyncPaisesError = 38;

    public final static int onPaisesSucess=39;

    public final static int onCategoriaEncargoSucess=40;

    public final static int onCuotasSucess=41;

    public final static int onPlazosSucess=42;
    public final static int onDescuentosSucess=43;
    public  final static int onFetchClienteSucess =44;

    public  final static int onFetchVentaSucess =45;
    public  final static int onFetchDevolucionSucess =46;

    public  final static int onFetchEncargosSucess =47;

    public  final static int UpdateClienteContador =48;
    public  final static int onClienteUpdateSucess =49;

    public  final static int ClienteContador =50;

    public  final static int CargarContadores =51;
    public  final static int OnMessage =52;

    public  final static int UpdateCarteraContador =53;
    public  final static int onCarteraUpdateSucess =54;

    public  final static int UpdateDevolucionesContador =55;
    public  final static int onDevolucionesUpdateSucess =56;

    public  final static int UpdateEncargosContador =57;
    public  final static int onEncargoUpdateSucess = 58;

    public  final static int UpdateVentasContador =59;
    public  final static int onVentasUpdateSucess =60;

    public  final static int UpdateCobroContador =61;
    public  final static int onUpdateCobroSucess =62;
    public  final static int onNetworkFails =63;

    public  final static int onCarteraDetalleDataSucess =64;

    public  final static int onSingOff =65;

    public  final static int onUpdateAmount =66;
    public  final static int onFetchCustomerSucess =67;

    private  int eventype;
    private  String errorMessage;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    private Object object;


    public int getEventype() {
        return eventype;
    }

    public void setEventype(int eventype) {
        this.eventype = eventype;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
