package yio.io.sifaapp.model.modelSend;

import java.util.List;

/**
 * Created by JUANCARLOS on 31/10/2016.
 */
public class AplFacturasProformas {


    public String Cedula ;

    public int objVendedorID ;

    public int objEstadoID ;

    public int objTerminoPagoID ;

    public int objModalidadPagoID ;

    public Integer objDescuentoID ;

    public String Fecha ;

    public Float Subtotal ;

    public Float Descuento ;

    public Float Total ;

    public Float Prima ;

    public Float Saldo ;

    public String observaciones ;

    public String UsuarioCreacion ;

    public AplFacturasProformas() {
    }

    public List<AplFacturasProformaDetalles> FacturaProformaDetalle;

    public List<AplFacturasProformaDetalles> getFacturaProformaDetalle() {
        return FacturaProformaDetalle;
    }

    public void setFacturaProformaDetalle(List<AplFacturasProformaDetalles> facturaProformaDetalle) {
        FacturaProformaDetalle = facturaProformaDetalle;
    }

    public String getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
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

    public int getObjModalidadPagoID() {
        return objModalidadPagoID;
    }

    public void setObjModalidadPagoID(int objModalidadPagoID) {
        this.objModalidadPagoID = objModalidadPagoID;
    }

    public Integer getObjDescuentoID() {
        return objDescuentoID;
    }

    public void setObjDescuentoID(Integer objDescuentoID) {
        this.objDescuentoID = objDescuentoID;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Float subtotal) {
        Subtotal = subtotal;
    }

    public Float getDescuento() {
        return Descuento;
    }

    public void setDescuento(Float descuento) {
        Descuento = descuento;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float total) {
        Total = total;
    }

    public Float getPrima() {
        return Prima;
    }

    public void setPrima(Float prima) {
        Prima = prima;
    }

    public Float getSaldo() {
        return Saldo;
    }

    public void setSaldo(Float saldo) {
        Saldo = saldo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
