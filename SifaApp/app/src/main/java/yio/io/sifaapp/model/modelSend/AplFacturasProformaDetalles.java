package yio.io.sifaapp.model.modelSend;

/**
 * Created by JUANCARLOS on 31/10/2016.
 */
public class AplFacturasProformaDetalles {



    public int objSivProductoID ;

    public int Cantidad;

    public Float Precio ;

    public Float Subtotal;

    public Float Descuento ;

    public Float Total ;

    public AplFacturasProformaDetalles() {

    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float total) {
        Total = total;
    }

    public Float getDescuento() {
        return Descuento;
    }

    public void setDescuento(Float descuento) {
        Descuento = descuento;
    }

    public Float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Float subtotal) {
        Subtotal = subtotal;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public Float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float precio) {
        Precio = precio;
    }

    public int getObjSivProductoID() {
        return objSivProductoID;
    }

    public void setObjSivProductoID(int objSivProductoID) {
        this.objSivProductoID = objSivProductoID;
    }
}
