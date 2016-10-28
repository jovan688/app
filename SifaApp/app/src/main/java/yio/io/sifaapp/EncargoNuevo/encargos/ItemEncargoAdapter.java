package yio.io.sifaapp.EncargoNuevo.encargos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import yio.io.sifaapp.R;

/**
 * Created by JUANCARLOS on 23/10/2016.
 */
public class ItemEncargoAdapter  extends RecyclerView.Adapter<ItemEncargoAdapter.EncargoItemViewHolder> {

    ArrayList<String> List;

    public ItemEncargoAdapter(ArrayList<String> List, Context context) {
        this.List = List;
    }

    @Override
    public ItemEncargoAdapter.EncargoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.encargorowitem, parent, false);
        EncargoItemViewHolder  viewHolder = new EncargoItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EncargoItemViewHolder holder, int position) {
        holder.text.setText(List.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void setdata( ArrayList<String> list){
        this.List = list;
    }

    public static class EncargoItemViewHolder extends RecyclerView.ViewHolder {

        protected TextView text;

        public EncargoItemViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text_id);
        }
    }
}