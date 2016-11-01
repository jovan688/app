package yio.io.sifaapp.model.modelSend;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;

import java.util.Date;

/**
 * Created by JUANCARLOS on 30/10/2016.
 */
public class cobro {

    private String Cedula;


    private String objSccCuentaID;


    private int objStbRutaID;


    private int objCobradorID;


    private Boolean Abono;


    private Boolean Cancelo;


    private String MotivoNoAbono;


    private double MontoAbonado;


    private double Saldo;


    private String FechaAbono;


    private String UsuarioCreacion;

    public cobro() {
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getObjSccCuentaID() {
        return objSccCuentaID;
    }

    public void setObjSccCuentaID(String objSccCuentaID) {
        this.objSccCuentaID = objSccCuentaID;
    }

    public int getObjStbRutaID() {
        return objStbRutaID;
    }

    public void setObjStbRutaID(int objStbRutaID) {
        this.objStbRutaID = objStbRutaID;
    }

    public int getObjCobradorID() {
        return objCobradorID;
    }

    public void setObjCobradorID(int objCobradorID) {
        this.objCobradorID = objCobradorID;
    }

    public Boolean getAbono() {
        return Abono;
    }

    public void setAbono(Boolean abono) {
        Abono = abono;
    }

    public Boolean getCancelo() {
        return Cancelo;
    }

    public void setCancelo(Boolean cancelo) {
        Cancelo = cancelo;
    }

    public String getMotivoNoAbono() {
        return MotivoNoAbono;
    }

    public void setMotivoNoAbono(String motivoNoAbono) {
        MotivoNoAbono = motivoNoAbono;
    }

    public double getMontoAbonado() {
        return MontoAbonado;
    }

    public void setMontoAbonado(Float montoAbonado) {
        MontoAbonado = montoAbonado;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Float saldo) {
        Saldo = saldo;
    }

    public String getFechaAbono() {
        return FechaAbono;
    }

    public void setFechaAbono(String fechaAbono) {
        FechaAbono = fechaAbono;
    }

    public String getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }
}
