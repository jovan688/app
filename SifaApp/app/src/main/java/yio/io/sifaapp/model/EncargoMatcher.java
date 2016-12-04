package yio.io.sifaapp.model;

/**
 * Created by jovan on 3/12/16.
 */

public class EncargoMatcher {

    private String productoName;
    private Integer productoID;

    public EncargoMatcher(){

    }

    public String getProductoName() {
        return productoName;
    }

    public void setProductoName(String productoName) {
        this.productoName = productoName;
    }

    public Integer getProductoID() {
        return productoID;
    }

    public void setProductoID(Integer productoID) {
        this.productoID = productoID;
    }
}
