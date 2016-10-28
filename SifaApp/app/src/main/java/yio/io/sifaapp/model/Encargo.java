package yio.io.sifaapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import yio.io.sifaapp.utils.SifacDataBase;

/**
 * Created by JUANCARLOS on 17/09/2016.
 */
@Table(databaseName = SifacDataBase.NAME)
public class Encargo extends BaseModel implements Serializable {


    @PrimaryKey(autoincrement = true)
    @Column
    public  int id;
    @Column
    private String cedula ;
    @Column
    private Integer objSrhEmpleadoID ;
    @Column
    private Integer UsuarioCreacion ;



    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column
    private Date fechaCreacion;

    private List<EncargoDetalle> detalle ;

    public List<EncargoDetalle> getdetalle(){
        if(detalle == null) {
            detalle = new Select().from(EncargoDetalle.class).where(String.format("encargoid=%d",this.id)).queryList();
        }
        return  detalle;
    }


    public Encargo() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public Integer getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(Integer usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }

    public Integer getObjSrhEmpleadoID() {
        return objSrhEmpleadoID;
    }

    public void setObjSrhEmpleadoID(Integer objSrhEmpleadoID) {
        this.objSrhEmpleadoID = objSrhEmpleadoID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
