package yio.io.sifaapp.model;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.List;

import yio.io.sifaapp.utils.SifacDataBase;


/**
 * Created by JUANCARLOS on 26/07/2016.
 */
@Table(databaseName = SifacDataBase.NAME)
public class Cartera  extends BaseModel implements Serializable {

    @Column
    @PrimaryKey
    private Long id;
    @Column
    private String Cedula;
    @Column
    private String Ciudad;
    @Column
    private Integer ClienteID;
    @Column
    private String Direccion;
    @Column
    private String FechaAbono;
    @Column
    private Float MontoCuota;
    @Column
    private String NombreCompleto;
    @Column
    private int OrdenCobro;
    @Column
    private String Pais;
    @Column
    private String RutaAsignada;
    @Column
    private Float Saldo;
    @Column
    private String SccCuentaID;
    @Column
    private Integer StbRutaID;
    @Column
    private Integer ojbCobradorID;
    @Column
    private Boolean offline ;

    @Column
    private Boolean cobrado ;



    @Column
    private  Integer CuotasVencidas;

    public Boolean getOffline() {
        return offline;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

    public Boolean getCobrado() {
        return cobrado;
    }

    public void setCobrado(Boolean cobrado) {
        this.cobrado = cobrado;
    }

    public Integer getCuotasVencidas() {
        return CuotasVencidas;
    }

    public void setCuotasVencidas(Integer cuotasVencidas) {
        CuotasVencidas = cuotasVencidas;
    }


/*
    @Column
    @ForeignKey( references = {@ForeignKeyReference(columnName = "SivProductoID", columnType = Long.class, foreignColumnName = "id")} , saveForeignKeyModel = false)
    private  List<Producto> productos ;

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }


    public List<Producto> getProductos() {
        return productos;
    }

*/


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Cartera() {
    }

    public Cartera(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public Integer getClienteID() {
        return ClienteID;
    }

    public void setClienteID(Integer ClienteID) {
        this.ClienteID = ClienteID;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getFechaAbono() {
        return FechaAbono;
    }

    public void setFechaAbono(String FechaAbono) {
        this.FechaAbono = FechaAbono;
    }

    public Float getMontoCuota() {
        return MontoCuota;
    }

    public void setMontoCuota(Float MontoCuota) {
        this.MontoCuota = MontoCuota;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }

    public int getOrdenCobro() {
        return OrdenCobro;
    }

    public void setOrdenCobro(int OrdenCobro) {
        this.OrdenCobro = OrdenCobro;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getRutaAsignada() {
        return RutaAsignada;
    }

    public void setRutaAsignada(String RutaAsignada) {
        this.RutaAsignada = RutaAsignada;
    }

    public Float getSaldo() {
        return Saldo;
    }

    public void setSaldo(Float Saldo) {
        this.Saldo = Saldo;
    }

    public String getSccCuentaID() {
        return SccCuentaID;
    }

    public void setSccCuentaID(String SccCuentaID) {
        this.SccCuentaID = SccCuentaID;
    }

    public Integer getStbRutaID() {
        return StbRutaID;
    }

    public void setStbRutaID(Integer StbRutaID) {
        this.StbRutaID = StbRutaID;
    }

    public Integer getOjbCobradorID() {
        return ojbCobradorID;
    }

    public void setOjbCobradorID(Integer ojbCobradorID) {
        this.ojbCobradorID = ojbCobradorID;
    }

}
