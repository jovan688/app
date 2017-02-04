package yio.io.sifaapp.dialogos.product;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
public interface productorepository {
    void getAllProducts();
    void getProductsByCustomerId(long customerId , boolean activos);

    void getCarteraDetalleByCustomerId(long customerId);

}
