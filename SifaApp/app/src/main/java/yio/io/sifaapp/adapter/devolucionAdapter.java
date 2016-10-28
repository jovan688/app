package yio.io.sifaapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import yio.io.sifaapp.R;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Devolucion;
import yio.io.sifaapp.model.modelSend.Venta;

/**
 * Created by JUANCARLOS on 19/10/2016.
 */
public class devolucionAdapter extends RecyclerView.Adapter<devolucionViewHolder> {


    List<Devolucion> list = new ArrayList<Devolucion>();
    Activity activity;
    Customer customer;

    public void setCustomer(Customer c){
        customer = c;
    }

    public void setData(List<Devolucion> lista) {
        this.list = lista;
    }


    public devolucionAdapter(Activity a , List<Devolucion> dataset) {
        this.list = dataset;
        activity = a;
    }


    @Override
    public devolucionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.devolucionlist_item, parent, false);
        devolucionViewHolder itemViewHolder = new devolucionViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(devolucionViewHolder holder, int position) {
        if(list.get(position) instanceof Devolucion) {
            Devolucion devolucion = list.get(position);
            if (devolucion != null) {
                holder.txtviewamount.setText(String.valueOf(devolucion.getTotalDevolucion()));
                holder.txtviewdatepay.setText(devolucion.getFecha().toString());
                holder.txtviewIndex.setText(String.valueOf(position + 1));
                Customer c = new Select().from(Customer.class).where(String.format("Cedula='%s'", devolucion.getCedula())).querySingle();
                if(c!=null)
                    holder.txtviewcustomername.setText(c.getNombreCliente());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
