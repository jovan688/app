package yio.io.sifaapp.model.modelSend;

import java.util.List;

/**
 * Created by JUANCARLOS on 30/10/2016.
 */
public class encargo {

    private String Cedula;
    private String objSrhEmpleadoID;
    private String UsuarioCreacion;
    private List<encargodetalle> EncargoDetalle ;

    public encargo() {
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getObjSrhEmpleadoID() {
        return objSrhEmpleadoID;
    }

    public void setObjSrhEmpleadoID(String objSrhEmpleadoID) {
        this.objSrhEmpleadoID = objSrhEmpleadoID;
    }

    public String getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }

    public List<encargodetalle> getEncargoDetalle() {
        return EncargoDetalle;
    }

    public void setEncargoDetalle(List<encargodetalle> encargoDetalle) {
        EncargoDetalle = encargoDetalle;
    }
}
