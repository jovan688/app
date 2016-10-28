package yio.io.sifaapp.model;

/**
 * Created by STARK on 15/06/2016.
 */
public class authenticationRequest {

    private String Login;
    private String Clave ;
    private String Imei;

    public authenticationRequest(String Login ,String Clave , String Imei){
        this.Login = Login;
        this.Clave = Clave;
        this.Imei = Imei;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

}
