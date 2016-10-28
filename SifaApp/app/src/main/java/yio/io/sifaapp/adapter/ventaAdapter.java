package yio.io.sifaapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import yio.io.sifaapp.Cartera.IDetallaCarteraView;
import yio.io.sifaapp.R;
import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.modelSend.Venta;

/**
 * Created by JUANCARLOS on 19/10/2016.
 */
public class ventaAdapter extends RecyclerView.Adapter<ventaViewHolder> {


    List<Venta> list = new ArrayList<Venta>();
    Activity activity;
    Customer customer;

    public void setCustomer(Customer c){
        customer = c;
    }

    public void setData(List<Venta> lista) {
        this.list = lista;
    }


    public ventaAdapter(Activity a ,  List<Venta> dataset) {
        this.list = dataset;
        activity = a;
    }


    @Override
    public ventaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ventalist_item, parent, false);
        ventaViewHolder itemViewHolder = new ventaViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ventaViewHolder holder, int position) {
        if(list.get(position) instanceof Venta) {
            Venta venta = list.get(position);
            if (venta != null) {
                holder.txtviewamount.setText(String.valueOf(venta.getTotal()));
                holder.txtviewdatepay.setText(venta.getFecha().toString());
                holder.txtviewIndex.setText(String.valueOf(position + 1));
                Customer c = new Select().from(Customer.class).where(String.format("Cedula='%s'", venta.getCedula())).querySingle();
                holder.txtviewcustomername.setText(c.getNombreCliente());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
