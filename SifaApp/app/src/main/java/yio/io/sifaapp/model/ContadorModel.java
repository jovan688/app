package yio.io.sifaapp.model;

/**
 * Created by JUANCARLOS on 30/10/2016.
 */
public class ContadorModel {

    private int clientes;
    private int ventas;
    private int cartera;
    private int devoluciones;

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public  int referencia;

    public int getEncargos() {
        return encargos;
    }

    public void setEncargos(int encargos) {
        this.encargos = encargos;
    }

    public int getClientes() {
        return clientes;
    }

    public void setClientes(int clientes) {
        this.clientes = clientes;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public int getCartera() {
        return cartera;
    }

    public void setCartera(int cartera) {
        this.cartera = cartera;
    }

    public int getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(int devoluciones) {
        this.devoluciones = devoluciones;
    }

    private int encargos;

}
