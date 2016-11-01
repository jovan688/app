package yio.io.sifaapp.model.modelSend;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.ColumnAlias;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

import yio.io.sifaapp.utils.SifacDataBase;

@Table(databaseName = SifacDataBase.NAME)
public class Venta extends BaseModel {

    @Column
    @PrimaryKey
    public String  cedula;
    @Column
    public int  objVendedorID;
    @Column
    public int  objEstadoID;
    @Column
    public int  objTerminoPagoID;
    @Column
    public int  objDescuentoID;
    @Column
    public String Fecha;
    @Column
    public float  Subtotal;
    @Column
    public float  Descuento;
    @Column
    public float  Total;

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    @Column

    public float  Prima;
    @Column
    public float  Saldo;
    @Column
    public String observaciones;
    @Column
    public int  UsuarioCreacion;
    @Column
    private Boolean offline;

    public int getObjModalidadPagoID() {
        return ObjModalidadPagoID;
    }

    public void setObjModalidadPagoID(int objModalidadPagoID) {
        ObjModalidadPagoID = objModalidadPagoID;
    }

    @Column
    private int ObjModalidadPagoID;

    public Boolean getOffline() {
        return offline;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

    private  List<FacturaProformaDetalle> FacturaProformaDetalle ;

    public List<FacturaProformaDetalle> getFacturaDetalle(){
        if(FacturaProformaDetalle==null){
            FacturaProformaDetalle = new Select().from(FacturaProformaDetalle.class).where(String.format("cedula='%s'",this.cedula)).queryList();
        }
        return  FacturaProformaDetalle;
    }

    /*
    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "FacturaProformaDetalle")
    public  List<FacturaProformaDetalle> getFacturaProformaDetalle() {
        return FacturaProformaDetalle;
    }
    */

    public Venta() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getObjVendedorID() {
        return objVendedorID;
    }

    public void setObjVendedorID(int objVendedorID) {
        this.objVendedorID = objVendedorID;
    }

    public int getObjEstadoID() {
        return objEstadoID;
    }

    public void setObjEstadoID(int objEstadoID) {
        this.objEstadoID = objEstadoID;
    }

    public int getObjTerminoPagoID() {
        return objTerminoPagoID;
    }

    public void setObjTerminoPagoID(int objTerminoPagoID) {
        this.objTerminoPagoID = objTerminoPagoID;
    }

    public int getObjDescuentoID() {
        return objDescuentoID;
    }

    public void setObjDescuentoID(int objDescuentoID) {
        this.objDescuentoID = objDescuentoID;
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

    public float getPrima() {
        return Prima;
    }

    public void setPrima(float prima) {
        Prima = prima;
    }

    public float getSaldo() {
        return Saldo;
    }

    public void setSaldo(float saldo) {
        Saldo = saldo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }



}
