package yio.io.sifaapp.model;

/**
 * Created by JUANCARLOS on 31/10/2016.
 */
public class DevolucionProductos {

    public int getClienteID() {
        return ClienteID;
    }

    public void setClienteID(int clienteID) {
        ClienteID = clienteID;
    }

    private int ClienteID;

    public int objSfaFacturaID;

    public int getSivProductoID() {
        return SivProductoID;
    }

    public void setSivProductoID(int sivProductoID) {
        SivProductoID = sivProductoID;
    }

    public int getObjSfaFacturaID() {
        return objSfaFacturaID;
    }

    public void setObjSfaFacturaID(int objSfaFacturaID) {
        this.objSfaFacturaID = objSfaFacturaID;
    }

    private  int SivProductoID;

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }

    private  float Precio ;


    public DevolucionProductos() {
    }

    public DevolucionProductos(int clienteID, int sivProductoID, int objSfaFacturaID) {
        ClienteID = clienteID;
        SivProductoID = sivProductoID;
        this.objSfaFacturaID = objSfaFacturaID;
    }
}
