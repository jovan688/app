package yio.io.sifaapp.dialogos.product;

import android.util.Log;

import java.util.List;

import yio.io.sifaapp.dialogos.IDialogView;
import yio.io.sifaapp.utils.EventBus;
import yio.io.sifaapp.utils.Events;
import yio.io.sifaapp.utils.GreenRobotEventBus;

/**
 * Created by JUANCARLOS on 16/09/2016.
 */
public class productopresenterIMP implements productopresenter {

    private EventBus eventbus;
    private  productointeractor interactor;
    private IDialogView view;

    public  productopresenterIMP( IDialogView view){
        this.eventbus = GreenRobotEventBus.getInstance();
        interactor = new productointeractorIMP();
        this.view = view;
    }


    @Override
    public void getAllProducts() {
        interactor.getAllProducts();
    }

    @Override
    public void getProductsByCustomerId(long customerId) {
        interactor.getProductsByCustomerId(customerId);
    }

    @Override
    public void onCreated() {
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        eventbus.unregister(this);
    }

    @Override
    public void onEventMainThread(Events event) {
        Log.d("productoPresenterImp","onEventMainThread");
        switch (event.getEventype()){
            case Events.onFetchDataSucess :
                view.onFetchData( (List<Object>)event.getObject());
                break;
        }
    }
}
