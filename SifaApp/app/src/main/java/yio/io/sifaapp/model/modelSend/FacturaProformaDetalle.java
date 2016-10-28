package yio.io.sifaapp.model.modelSend;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import yio.io.sifaapp.utils.SifacDataBase;

@Table(databaseName = SifacDataBase.NAME)
public class FacturaProformaDetalle extends BaseModel {

    @Column
    @PrimaryKey
    public int  objSivProductoID;
    @Column
    public int  Cantidad;
    @Column
    public float  Precio;
    @Column
    public float  Subtotal;
    @Column
    public float  Descuento;
    @Column
    public float  Total;
    @Column
    public String  cedula;

    public FacturaProformaDetalle() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getObjSivProductoID() {
        return objSivProductoID;
    }

    public void setObjSivProductoID(int objSivProductoID) {
        this.objSivProductoID = objSivProductoID;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }

    public float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(float subtotal) {
        Subtotal = subtotal;
    }

    public float getDescuento() {
        return Descuento;
    }

    public void setDescuento(float descuento) {
        Descuento = descuento;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }
}
