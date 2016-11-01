package yio.io.sifaapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.Date;

import yio.io.sifaapp.utils.SifacDataBase;

/**
 * Created by JUANCARLOS on 17/10/2016.
 */
@Table(databaseName = SifacDataBase.NAME)
public class Devolucion extends BaseModel implements Serializable {

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Column
    @PrimaryKey
    private String Id;

    @Column
    private String Cedula;
    @Column
    public Integer objSccCuentaID;
    @Column
    public Integer objStbRutaID;
    @Column
    public Integer objSivProductoID;
    @Column
    public Integer objSfaFacturaID;
    @Column
    public Integer objVendedorID;
    @Column
    public String RazonDevolucion;
    @Column
    public String Fecha;
    @Column
    public Float TotalDevolucion;

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    @Column

    public Integer UsuarioCreacion;

    @Column
    private Boolean offline;

    public Boolean getOffline() {
        return offline;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

    public Devolucion() {}



    public Integer getObjSccCuentaID() {
        return objSccCuentaID;
    }

    public void setObjSccCuentaID(Integer objSccCuentaID) {
        this.objSccCuentaID = objSccCuentaID;
    }

    public Integer getObjStbRutaID() {
        return objStbRutaID;
    }

    public void setObjStbRutaID(Integer objStbRutaID) {
        this.objStbRutaID = objStbRutaID;
    }

    public Integer getObjSivProductoID() {
        return objSivProductoID;
    }

    public void setObjSivProductoID(Integer objSivProductoID) {
        this.objSivProductoID = objSivProductoID;
    }

    public Integer getObjSfaFacturaID() {
        return objSfaFacturaID;
    }

    public void setObjSfaFacturaID(Integer objSfaFacturaID) {
        this.objSfaFacturaID = objSfaFacturaID;
    }

    public Integer getObjVendedorID() {
        return objVendedorID;
    }

    public void setObjVendedorID(Integer objVendedorID) {
        this.objVendedorID = objVendedorID;
    }

    public String getRazonDevolucion() {
        return RazonDevolucion;
    }

    public void setRazonDevolucion(String razonDevolucion) {
        RazonDevolucion = razonDevolucion;
    }


    public Float getTotalDevolucion() {
        return TotalDevolucion;
    }

    public void setTotalDevolucion(Float totalDevolucion) {
        TotalDevolucion = totalDevolucion;
    }

    public Integer getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(Integer usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }



}
