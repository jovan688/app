package yio.io.sifaapp.model.modelSend;

/**
 * Created by JUANCARLOS on 30/10/2016.
 */
public class devolucion {

    private String Cedula ;
    private int objSccCuentaID ;
    private int objStbRutaID ;
    private int objSivProductoID ;
    private int objSfaFacturaID ;
    private int objVendedorID ;
    private String RazonDevolucion ;
    private String Fecha ;
    private Float TotalDevolucion ;
    private String UsuarioCreacion ;

    public devolucion() {
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public int getObjSccCuentaID() {
        return objSccCuentaID;
    }

    public void setObjSccCuentaID(int objSccCuentaID) {
        this.objSccCuentaID = objSccCuentaID;
    }

    public void setTotalDevolucion(Float totalDevolucion) {
        TotalDevolucion = totalDevolucion;
    }

    public void setObjSfaFacturaID(int objSfaFacturaID) {
        this.objSfaFacturaID = objSfaFacturaID;
    }

    public int getObjStbRutaID() {
        return objStbRutaID;
    }

    public void setObjStbRutaID(int objStbRutaID) {
        this.objStbRutaID = objStbRutaID;
    }

    public int getObjSivProductoID() {
        return objSivProductoID;
    }

    public void setObjSivProductoID(int objSivProductoID) {
        this.objSivProductoID = objSivProductoID;
    }

    public int getObjVendedorID() {
        return objVendedorID;
    }

    public void setObjVendedorID(int objVendedorID) {
        this.objVendedorID = objVendedorID;
    }

    public String getRazonDevolucion() {
        return RazonDevolucion;
    }

    public void setRazonDevolucion(String razonDevolucion) {
        RazonDevolucion = razonDevolucion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public float getTotalDevolucion() {
        return TotalDevolucion;
    }

    public void setTotalDevolucion(float totalDevolucion) {
        TotalDevolucion = totalDevolucion;
    }

    public String getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }
}
