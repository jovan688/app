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
import yio.io.sifaapp.model.Encargo;

/**
 * Created by JUANCARLOS on 19/10/2016.
 */
public class encargoAdapter extends RecyclerView.Adapter<encargoViewHolder> {


    List<Encargo> list = new ArrayList<Encargo>();
    Activity activity;
    Customer customer;

    public void setCustomer(Customer c){
        customer = c;
    }

    public void setData(List<Encargo> lista) {
        this.list = lista;
    }


    public encargoAdapter(Activity a , List<Encargo> dataset) {
        this.list = dataset;
        activity = a;
    }


    @Override
    public encargoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.encargolist_item, parent, false);
        encargoViewHolder itemViewHolder = new encargoViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(encargoViewHolder holder, int position) {
        if(list.get(position) instanceof Encargo) {
            Encargo encargo = list.get(position);
            if (encargo != null) {
                holder.txtviewdatepay.setText(encargo.getFechaCreacion().toString());
                holder.txtviewIndex.setText(String.valueOf(position + 1));
                Customer c = new Select().from(Customer.class).where(String.format("Cedula='%s'", encargo.getCedula())).querySingle();
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
