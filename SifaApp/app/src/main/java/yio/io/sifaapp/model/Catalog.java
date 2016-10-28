package yio.io.sifaapp.model;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import yio.io.sifaapp.utils.SifacDataBase;
/**
 * Entity mapped to table "CATALOG".
 */

@Table(databaseName = SifacDataBase.NAME)
public class Catalog extends BaseModel {

    @Column
    private Boolean CatalogoActivo;
    @Column
    private String CatalogoDescripcion;
    @Column
    private String Codigo;
    @Column
    private String Descripcion;
    @Column
    private String Nombre;
    @PrimaryKey
    @Column
    private Integer StbValorCatalogoID;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Catalog() {
    }


    public Catalog( Boolean Activo, String CatalogoDescripcion, String Codigo, String Descripcion, String Nombre, Integer StbValorCatalogoID) {
        this.CatalogoActivo = CatalogoActivo;
        this.CatalogoDescripcion = CatalogoDescripcion;
        this.Codigo = Codigo;
        this.Descripcion = Descripcion;
        this.Nombre = Nombre;
        this.StbValorCatalogoID = StbValorCatalogoID;
    }



    public Boolean getCatalogoActivo() {
        return CatalogoActivo;
    }

    public void setCatalogoActivo(Boolean CatalogoActivo) {
        this.CatalogoActivo = CatalogoActivo;
    }

    public String getCatalogoDescripcion() {
        return CatalogoDescripcion;
    }

    public void setCatalogoDescripcion(String CatalogoDescripcion) {
        this.CatalogoDescripcion = CatalogoDescripcion;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Integer getStbValorCatalogoID() {
        return StbValorCatalogoID;
    }

    public void setStbValorCatalogoID(Integer StbValorCatalogoID) {
        this.StbValorCatalogoID = StbValorCatalogoID;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END
    public String toString() {
        return Descripcion;
    }
}
