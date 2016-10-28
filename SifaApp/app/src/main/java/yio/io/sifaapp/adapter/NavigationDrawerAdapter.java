package yio.io.sifaapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import yio.io.sifaapp.R;
import yio.io.sifaapp.model.NavigationDrawerItem;

/**
 * Created by JUANCARLOS on 06/08/2016.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {

    private List<NavigationDrawerItem> mdatalist = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private OnItemClickListener listener;

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> mdatalist , OnItemClickListener listener) {
        this.mdatalist = mdatalist;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NavigationDrawerItem current = mdatalist.get(position);
        holder.title.setText(current.getTitle());
        holder.setClickListener(position , current.getTitle() , listener);

        /*holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,holder.title.getText(),Toast.LENGTH_SHORT).show();

            }
        });*/
        if((mdatalist.size()-1) == position){
            holder.imageView.setBackgroundResource(R.drawable.ic_exit_to_app_white_48dp);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mdatalist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        View itemview;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemview = itemView;
            this.title= (TextView) itemView.findViewById(R.id.title);
            this.imageView = (ImageView) itemView.findViewById(R.id.exit);
        }

        public  void  setClickListener (final int position ,final String title, final OnItemClickListener listener){
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSeleted(position);
                }
            });
        }
    }
}
