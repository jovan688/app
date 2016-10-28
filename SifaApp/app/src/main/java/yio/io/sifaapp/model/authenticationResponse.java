package yio.io.sifaapp.model;

import java.util.List;

/**
 * Created by STARK on 15/06/2016.
 */
public class authenticationResponse {

    private List<Action> Acciones;
    private String Clave;
    private String Login;
    private Integer SsgCuentaID;
    private Boolean TieneAcceso;
    private Integer objEmpleadoID;

    public List<Action> getAcciones() {
        return Acciones;
    }

    public void setAcciones(List<Action> acciones) {
        Acciones = acciones;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public Integer getSsgCuentaID() {
        return SsgCuentaID;
    }

    public void setSsgCuentaID(Integer ssgCuentaID) {
        SsgCuentaID = ssgCuentaID;
    }

    public Boolean getTieneAcceso() {
        return TieneAcceso;
    }

    public void setTieneAcceso(Boolean tieneAcceso) {
        TieneAcceso = tieneAcceso;
    }

    public Integer getObjEmpleadoID() {
        return objEmpleadoID;
    }

    public void setObjEmpleadoID(Integer objEmpleadoID) {
        this.objEmpleadoID = objEmpleadoID;
    }

    public class Action {

        private String CodigoAccion;
        private String NombreAccion;

        public Action(String codigoAccion, String nombreAccion) {
            CodigoAccion = codigoAccion;
            NombreAccion = nombreAccion;
        }

        public String getCodigoAccion() {
            return CodigoAccion;
        }

        public void setCodigoAccion(String codigoAccion) {
            CodigoAccion = codigoAccion;
        }

        public String getNombreAccion() {
            return NombreAccion;
        }

        public void setNombreAccion(String nombreAccion) {
            NombreAccion = nombreAccion;
        }
    }
}
