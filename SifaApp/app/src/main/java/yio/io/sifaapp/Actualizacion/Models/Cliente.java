package yio.io.sifaapp.Actualizacion.Models;

import com.raizlabs.android.dbflow.annotation.Column;

import java.util.Date;

/**
 * Created by JUANCARLOS on 26/10/2016.
 */
public class Cliente {

    public Integer getObjRutaID() {
        return objRutaID;
    }

    public void setObjRutaID(Integer objRutaID) {
        this.objRutaID = objRutaID;
    }

    private Integer objRutaID;
    private Integer OrdenCobro;
    private String Nombre1;
    private String Nombre2;
    private String Apellido2;
    private String Apellido1;
    private Integer objGeneroID;
    private String Cedula;
    private String Direccion;
    private String Telefonos;
    private Integer objPaisID;
    private Integer objCiudadID;
    private String UsuarioCreacion;

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    private String Referencia;
    public int getClienteID() {
        return ClienteID;
    }

    public void setClienteID(int clienteID) {
        ClienteID = clienteID;
    }

    private int ClienteID;

    public Integer getObjTipoEntradaID() {
        return objTipoEntradaID;
    }

    public void setObjTipoEntradaID(Integer objTipoEntradaID) {
        this.objTipoEntradaID = objTipoEntradaID;
    }

    private Integer objTipoEntradaID;

    public String getParamVerificacion() {
        return ParamVerificacion;
    }

    public void setParamVerificacion(String paramVerificacion) {
        ParamVerificacion = paramVerificacion;
    }

    private String ParamVerificacion;

    public Cliente() {

    }

    public Integer getOrdenCobro() {
        return OrdenCobro;
    }

    public void setOrdenCobro(Integer ordenCobro) {
        OrdenCobro = ordenCobro;
    }

    public String getNombre1() {
        return Nombre1;
    }

    public void setNombre1(String nombre1) {
        Nombre1 = nombre1;
    }

    public String getNombre2() {
        return Nombre2;
    }

    public void setNombre2(String nombre2) {
        Nombre2 = nombre2;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String apellido2) {
        Apellido2 = apellido2;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String apellido1) {
        Apellido1 = apellido1;
    }

    public Integer getObjGeneroID() {
        return objGeneroID;
    }

    public void setObjGeneroID(Integer objGeneroID) {
        this.objGeneroID = objGeneroID;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }


    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefonos() {
        return Telefonos;
    }

    public void setTelefonos(String telefonos) {
        Telefonos = telefonos;
    }

    public Integer getObjPaisID() {
        return objPaisID;
    }

    public void setObjPaisID(Integer objPaisID) {
        this.objPaisID = objPaisID;
    }

    public Integer getObjCiudadID() {
        return objCiudadID;
    }

    public void setObjCiudadID(Integer objCiudadID) {
        this.objCiudadID = objCiudadID;
    }

    public String getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }


}
