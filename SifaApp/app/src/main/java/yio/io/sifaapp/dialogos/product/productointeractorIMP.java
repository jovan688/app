package yio.io.sifaapp.dialogos.product;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
public class productointeractorIMP implements productointeractor {


    private productorepository repository;

    public productointeractorIMP() {
        repository = new productorepositoryIMP();
    }

    @Override
    public void getAllProducts() {
        repository.getAllProducts();
    }

    @Override
    public void getProductsByCustomerId(long customerId, boolean activos) {
        repository.getProductsByCustomerId(customerId, activos);
    }

}
