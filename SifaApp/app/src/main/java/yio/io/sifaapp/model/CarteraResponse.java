package yio.io.sifaapp.model;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import yio.io.sifaapp.utils.SifacDataBase;
/**
 * Created by JUANCARLOS on 26/07/2016.
 */

public class CarteraResponse{


    private Long id;

    private String Cedula;

    private String Ciudad;

    private Integer ClienteID;

    private String Direccion;

    private String FechaAbono;

    private Float MontoCuota;

    private String NombreCompleto;

    private String OrdenCobro;

    private String Pais;

    private String RutaAsignada;

    private Float Saldo;

    private String SccCuentaID;

    private Integer StbRutaID;

    private Integer ojbCobradorID;

    public Integer getCuotasVencidas() {
        return CuotasVencidas;
    }

    public void setCuotasVencidas(Integer cuotasVencidas) {
        CuotasVencidas = cuotasVencidas;
    }

    private Integer CuotasVencidas;

    public void setProductos(List<Productos> productos) {
        this.Productos = productos;
    }

    List<Productos> Productos ;

    public List<Productos> getProductos() {
        return Productos;
    }




    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public CarteraResponse() {
    }

    public CarteraResponse(Long id) {
        this.id = id;
    }

    public CarteraResponse(Long id, String Cedula, String Ciudad, Integer ClienteID, String Direccion, String FechaAbono, Float MontoCuota, String NombreCompleto, String OrdenCobro, String Pais, String RutaAsignada, Float Saldo, String SccCuentaID, Integer StbRutaID, Integer ojbCobradorID) {
        this.id = id;
        this.Cedula = Cedula;
        this.Ciudad = Ciudad;
        this.ClienteID = ClienteID;
        this.Direccion = Direccion;
        this.FechaAbono = FechaAbono;
        this.MontoCuota = MontoCuota;
        this.NombreCompleto = NombreCompleto;
        this.OrdenCobro = OrdenCobro;
        this.Pais = Pais;
        this.RutaAsignada = RutaAsignada;
        this.Saldo = Saldo;
        this.SccCuentaID = SccCuentaID;
        this.StbRutaID = StbRutaID;
        this.ojbCobradorID = ojbCobradorID;
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

    public String getOrdenCobro() {
        return OrdenCobro;
    }

    public void setOrdenCobro(String OrdenCobro) {
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
