package yio.io.sifaapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import yio.io.sifaapp.utils.SifacDataBase;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
@Table(databaseName = SifacDataBase.NAME)
public class Pais extends BaseModel {
    @Column
    private String A2 ;
    @Column
    private String A3 ;
    @Column
    private String Nombre ;
    @Column
    @PrimaryKey
    private int StbPaisID ;

    public Pais() {
    }

    public String getA2() {
        return A2;
    }

    public void setA2(String a2) {
        A2 = a2;
    }

    public int getStbPaisID() {
        return StbPaisID;
    }

    public void setStbPaisID(int stbPaisID) {
        StbPaisID = stbPaisID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getA3() {
        return A3;
    }

    public void setA3(String a3) {
        A3 = a3;
    }

    public String toString() {
        return Nombre;
    }
}
