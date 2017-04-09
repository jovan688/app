package yio.io.sifaapp.Actualizacion.Models;

/**
 * Created by jovan on 5/4/17.
 */

public class OrdenCliente {

    private int ClienteID;
    private int OrdenCobro;
    private int objRutaID;
    // {"OrdenesClientes":[{"ClienteID":3686,"OrdenCobro":3,"objRutaID":143},{"ClienteID":4081,"OrdenCobro":5,"objRutaID":14}]}

    public OrdenCliente(int clienteID, int ordenCobro, int objRutaID) {
        ClienteID = clienteID;
        OrdenCobro = ordenCobro;
        this.objRutaID = objRutaID;
    }

    public int getClienteID() {
        return ClienteID;
    }

    public void setClienteID(int clienteID) {
        ClienteID = clienteID;
    }

    public int getOrdenCobro() {
        return OrdenCobro;
    }

    public void setOrdenCobro(int ordenCobro) {
        OrdenCobro = ordenCobro;
    }

    public int getObjRutaID() {
        return objRutaID;
    }

    public void setObjRutaID(int objRutaID) {
        this.objRutaID = objRutaID;
    }
}
