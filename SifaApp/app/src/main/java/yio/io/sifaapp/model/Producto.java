package yio.io.sifaapp.model;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

import yio.io.sifaapp.utils.SifacDataBase;

/**
 * Entity mapped to table "PRODUCTO".
 */
@Table(databaseName = SifacDataBase.NAME)
public class Producto  extends BaseModel implements Serializable {
    @Column
    @PrimaryKey
    private Integer SivProductoID;
    @Column
    private Integer Cantidad_Minima;
    @Column
    private Float CostoPromedio;
    @Column
    private String Descripcion;
    @Column
    private Float Margen_Utilidad_Contado;
    @Column
    private Float Margen_Utilidad_Credito;
    @Column
    private String Nombre;
    @Column
    private Float Precio_Contado;
    @Column
    private Float Precio_Credito;
    @Column
    private Integer objCategoriaID;
    @Column
    private Integer objMarcaID;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Producto() {
    }

    public Producto(Integer sivProductoID) {
        SivProductoID = sivProductoID;
    }

    public Producto(Integer sivProductoID, Integer cantidad_Minima, Float costoPromedio, String descripcion, Float margen_Utilidad_Contado, Float margen_Utilidad_Credito, String nombre, Float precio_Contado, Float precio_Credito, Integer objCategoriaID, Integer objMarcaID) {
        SivProductoID = sivProductoID;
        Cantidad_Minima = cantidad_Minima;
        CostoPromedio = costoPromedio;
        Descripcion = descripcion;
        Margen_Utilidad_Contado = margen_Utilidad_Contado;
        Margen_Utilidad_Credito = margen_Utilidad_Credito;
        Nombre = nombre;
        Precio_Contado = precio_Contado;
        Precio_Credito = precio_Credito;
        this.objCategoriaID = objCategoriaID;
        this.objMarcaID = objMarcaID;
    }

    public Integer getCantidad_Minima() {
        return Cantidad_Minima;
    }

    public void setCantidad_Minima(Integer Cantidad_Minima) {
        this.Cantidad_Minima = Cantidad_Minima;
    }

    public Float getCostoPromedio() {
        return CostoPromedio;
    }

    public void setCostoPromedio(Float CostoPromedio) {
        this.CostoPromedio = CostoPromedio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Float getMargen_Utilidad_Contado() {
        return Margen_Utilidad_Contado;
    }

    public void setMargen_Utilidad_Contado(Float Margen_Utilidad_Contado) {
        this.Margen_Utilidad_Contado = Margen_Utilidad_Contado;
    }

    public Float getMargen_Utilidad_Credito() {
        return Margen_Utilidad_Credito;
    }

    public void setMargen_Utilidad_Credito(Float Margen_Utilidad_Credito) {
        this.Margen_Utilidad_Credito = Margen_Utilidad_Credito;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Float getPrecio_Contado() {
        return Precio_Contado;
    }

    public void setPrecio_Contado(Float Precio_Contado) {
        this.Precio_Contado = Precio_Contado;
    }

    public Float getPrecio_Credito() {
        return Precio_Credito;
    }

    public void setPrecio_Credito(Float Precio_Credito) {
        this.Precio_Credito = Precio_Credito;
    }

    public Integer getSivProductoID() {
        return SivProductoID;
    }

    public void setSivProductoID(Integer SivProductoID) {
        this.SivProductoID = SivProductoID;
    }

    public Integer getObjCategoriaID() {
        return objCategoriaID;
    }

    public void setObjCategoriaID(Integer objCategoriaID) {
        this.objCategoriaID = objCategoriaID;
    }

    public Integer getObjMarcaID() {
        return objMarcaID;
    }

    public void setObjMarcaID(Integer objMarcaID) {
        this.objMarcaID = objMarcaID;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END
    public Object isMatch(CharSequence constraint) {
        if (getNombre().toLowerCase().contains(constraint.toString()))
            return true;
        return false;
    }
}
