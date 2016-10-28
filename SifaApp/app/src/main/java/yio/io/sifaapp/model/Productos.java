package yio.io.sifaapp.model;

import java.math.BigDecimal;

/**
 * Created by JUANCARLOS on 03/08/2016.
 */
public class Productos {
    private BigDecimal Precio;
    private int SivProductoID;

    public int getObjSfaFacturaID() {
        return objSfaFacturaID;
    }

    public void setObjSfaFacturaID(int objSfaFacturaID) {
        this.objSfaFacturaID = objSfaFacturaID;
    }

    private int objSfaFacturaID;

    public Productos() {
    }

    public BigDecimal getPrecio() {
        return Precio;
    }

    public void setPrecio(BigDecimal precio) {
        Precio = precio;
    }

    public int getSivProductoID() {
        return SivProductoID;
    }

    public void setSivProductoID(int sivProductoID) {
        SivProductoID = sivProductoID;
    }
}
