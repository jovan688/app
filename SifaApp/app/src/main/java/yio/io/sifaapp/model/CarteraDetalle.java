package yio.io.sifaapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

import yio.io.sifaapp.utils.SifacDataBase;

/**
 * Created by JUANCARLOS on 11/09/2016.
 */
@Table(databaseName = SifacDataBase.NAME)
public class CarteraDetalle extends BaseModel implements Serializable {


    @PrimaryKey
    @Column
    private int ClienteID;
    @PrimaryKey
    @Column
    private  int SivProductoID;
    @Column
    private Float Precio;
    @Column
    public int objSfaFacturaID;
    @Column
    private String cedula;

    public String getCedula() {
        return cedula;
    }
    public int getObjSfaFacturaID() {
        return objSfaFacturaID;
    }

    public void setObjSfaFacturaID(int objSfaFacturaID) {
        this.objSfaFacturaID = objSfaFacturaID;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public CarteraDetalle() {
    }

    public CarteraDetalle(int clienteID, int objSfaFacturaID, int sivProductoID, Float precio, String cedula) {
        ClienteID = clienteID;
        this.objSfaFacturaID = objSfaFacturaID;
        SivProductoID = sivProductoID;
        Precio = precio;
        this.cedula = cedula;
    }

    public int getClienteID() {
        return ClienteID;
    }

    public void setClienteID(int clienteID) {
        ClienteID = clienteID;
    }
    public Float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float precio) {
        Precio = precio;
    }

    public int getSivProductoID() {
        return SivProductoID;
    }

    public void setSivProductoID(int sivProductoID) {
        SivProductoID = sivProductoID;
    }
}
