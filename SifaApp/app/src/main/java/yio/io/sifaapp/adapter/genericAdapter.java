package yio.io.sifaapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import yio.io.sifaapp.R;

/**
 * Created by JUANCARLOS on 11/08/2016.
 */
public class genericAdapter extends RecyclerView.Adapter<holder> {


    List<Object> list = new ArrayList<Object>();

    public genericAdapter(List<Object> list) {
        this.list = list;
    }


    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        holder itemViewHolder = new holder(view);

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {
        Item row = (Item) this.list.get(position);
        holder.txtviewcustomername.setText(row.getCustomerName());
        holder.txtviewamount.setText(row.getAmount());
        holder.txtviewdatepay.setText(row.getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
