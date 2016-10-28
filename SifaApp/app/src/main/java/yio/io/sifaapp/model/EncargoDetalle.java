package yio.io.sifaapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

import yio.io.sifaapp.utils.SifacDataBase;

/**
 * Created by JUANCARLOS on 17/09/2016.
 */
@Table(databaseName = SifacDataBase.NAME)
public class EncargoDetalle  extends BaseModel implements Serializable {


    @PrimaryKey(autoincrement = true)
    @Column
    public  int detalle_id;

    @Column
    private Integer objCategoriaID ;
    @Column
    private String Nombre_Producto ;
    @Column
    private String Observaciones ;
    @Column
    private Integer SivProductoID;

    @Column
    private int encargoid;

    public int getEncargoid() {
        return encargoid;
    }

    public void setEncargoid(int encargoid) {
        this.encargoid = encargoid;
    }

    public long getDetalle_id() {
        return detalle_id;
    }

    public void setDetalle_id(Integer detalle_id) {
        this.detalle_id = detalle_id;
    }



    public EncargoDetalle() {
    }

    public Integer getObjCategoriaID() {
        return objCategoriaID;
    }

    public void setObjCategoriaID(Integer objCategoriaID) {
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

    public Integer getSivProductoID() {
        return SivProductoID;
    }

    public void setSivProductoID(Integer sivProductoID) {
        SivProductoID = sivProductoID;
    }

}
