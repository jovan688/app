package yio.io.sifaapp.model.modelSend;

/**
 * Created by JUANCARLOS on 30/10/2016.
 */
public class encargodetalle {

    private int objCategoriaID;
    private String Nombre_Producto;
    private String Observaciones;

    public Integer getObjProductoID() {
        return objProductoID;
    }

    public void setObjProductoID(Integer objProductoID) {
        this.objProductoID = objProductoID;
    }

    private Integer objProductoID;

    public encargodetalle() {
    }



    public int getObjCategoriaID() {
        return objCategoriaID;
    }

    public void setObjCategoriaID(int objCategoriaID) {
        this.objCategoriaID = objCategoriaID;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String nombre_Producto) {
        Nombre_Producto = nombre_Producto;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }
}
