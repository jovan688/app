package yio.io.sifaapp.model;

import java.util.List;

import yio.io.sifaapp.Actualizacion.Models.OrdenCliente;

/**
 * Created by jovan on 8/4/17.
 */

public class OrdenesClientes {

    public List<OrdenCliente> getOrdenClientes() {
        return OrdenClientes;
    }

    public void setOrdenClientes(List<OrdenCliente> ordenClientes) {
        OrdenClientes = ordenClientes;
    }

    private List<OrdenCliente> OrdenClientes ;

    public OrdenesClientes() {
    }


}
