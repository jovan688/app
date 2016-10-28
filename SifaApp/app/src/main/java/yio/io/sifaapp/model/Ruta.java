package yio.io.sifaapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import yio.io.sifaapp.utils.SifacDataBase;

/**
 * Created by JUANCARLOS on 01/08/2016.
 */

@Table(databaseName = SifacDataBase.NAME)
public class Ruta extends BaseModel {

    @Column
    @PrimaryKey
    private int StbRutaID;
    @Column
    private String CargarDiferenciada;
    @Column
    private String DiaCobro;
    @Column
    private String Nombre;
    @Column
    private int objCiudadID;
    @Column
    private int objPaisID;
    @Column
    private String objSupervisor;

    public Ruta() {
    }

    public Ruta(int stbRutaID, String cargarDiferenciada, String diaCobro, String nombre, int objCiudadID, int objPaisID, String objSupervisor) {
        StbRutaID = stbRutaID;
        CargarDiferenciada = cargarDiferenciada;
        DiaCobro = diaCobro;
        Nombre = nombre;
        this.objCiudadID = objCiudadID;
        this.objPaisID = objPaisID;
        this.objSupervisor = objSupervisor;
    }

    public int getStbRutaID() {
        return StbRutaID;
    }

    public void setStbRutaID(int stbRutaID) {
        StbRutaID = stbRutaID;
    }

    public String getCargarDiferenciada() {
        return CargarDiferenciada;
    }

    public void setCargarDiferenciada(String cargarDiferenciada) {
        CargarDiferenciada = cargarDiferenciada;
    }

    public String getDiaCobro() {
        return DiaCobro;
    }

    public void setDiaCobro(String diaCobro) {
        DiaCobro = diaCobro;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getObjCiudadID() {
        return objCiudadID;
    }

    public void setObjCiudadID(int objCiudadID) {
        this.objCiudadID = objCiudadID;
    }

    public int getObjPaisID() {
        return objPaisID;
    }

    public void setObjPaisID(int objPaisID) {
        this.objPaisID = objPaisID;
    }

    public String getObjSupervisor() {
        return objSupervisor;
    }

    public void setObjSupervisor(String objSupervisor) {
        this.objSupervisor = objSupervisor;
    }

    public String toString() {
        return Nombre;
    }
}
