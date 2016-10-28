package yio.io.sifaapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.Date;

import yio.io.sifaapp.utils.SifacDataBase;

/**
 * Created by JUANCARLOS on 14/09/2016.
 */
@Table(databaseName = SifacDataBase.NAME)
public class Cobro extends BaseModel {


    @Column
    private int objSccClienteID;
    @PrimaryKey
    @Column
    private int objSccCuentaID;

    @Column
    private int objStbRutaID;

    @Column
    private int objCobradorID;

    @Column
    private Boolean Abono;

    @Column
    private Boolean Cancelo;

    @Column
    private String MotivoNoAbono;

    @Column
    private Float MontoAbonado;

    @Column
    private Float Saldo;

    @Column
    private Date FechaAbono;

    @Column
    private int UsuarioCreacion;

    public Boolean getOffline() {
        return offline;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

    @Column
    private Boolean offline;

    public Cobro() {
    }

    public int getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }

    public Date getFechaAbono() {
        return FechaAbono;
    }

    public void setFechaAbono(Date fechaAbono) {
        FechaAbono = fechaAbono;
    }

    public int getObjSccClienteID() {
        return objSccClienteID;
    }

    public void setObjSccClienteID(int objSccClienteID) {
        this.objSccClienteID = objSccClienteID;
    }

    public int getObjSccCuentaID() {
        return objSccCuentaID;
    }

    public void setObjSccCuentaID(int objSccCuentaID) {
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

    public Float getMontoAbonado() {
        return MontoAbonado;
    }

    public void setMontoAbonado(Float montoAbonado) {
        MontoAbonado = montoAbonado;
    }

    public Float getSaldo() {
        return Saldo;
    }

    public void setSaldo(Float saldo) {
        Saldo = saldo;
    }
}
