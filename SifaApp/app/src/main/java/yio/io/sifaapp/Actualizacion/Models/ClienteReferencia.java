package yio.io.sifaapp.Actualizacion.Models;

/**
 * Created by jovan on 4/2/17.
 */

public class ClienteReferencia {

    private  int ClienteID;
    private  String Referencia;

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public int getClienteID() {
        return ClienteID;
    }

    public void setClienteID(int clienteID) {
        ClienteID = clienteID;
    }
}
