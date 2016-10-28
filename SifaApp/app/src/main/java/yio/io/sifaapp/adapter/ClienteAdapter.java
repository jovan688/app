package yio.io.sifaapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import yio.io.sifaapp.R;
import yio.io.sifaapp.dialogos.CustomerDialog;
import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Producto;

/**
 * Created by JUANCARLOS on 15/09/2016.
 */
public class ClienteAdapter extends RecyclerView.Adapter<ClienteHolder> implements Filterable {

    List<Customer> list = new ArrayList<Customer>();
    private List<Customer> mOriginalValues;
    private setOnLongClickListener mButtonClickListener;
    private int selectedPos=0;
    private int positionCache;
    Context context;

    public ClienteAdapter(Context context , setOnLongClickListener listener) {
        mButtonClickListener = listener;
        this.context = context;
    }

    @Override
    public ClienteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_row, parent, false);
        ClienteHolder itemViewHolder = new ClienteHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ClienteHolder holder, final int position) {
        if(list.get(position) instanceof Customer) {
            final Customer customer = list.get(position);
            if (customer != null) {
                holder.txtviewcustomername.setText(customer.getNombreCliente());
                holder.txtviewcedula.setText(customer.getCedula());
            }
        /*
        holder.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mButtonClickListener.onLongButtonClick(position,customer);
                return false;
            }
        });

        */

            holder.setOnLongClickListener(position, customer, mButtonClickListener);


            if (getSelectedPosition() == position) {
                holder.getView().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.action_item_selected));
            } else {
                holder.getView().setBackgroundResource(android.R.color.transparent);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  void  setData(List<Customer> lista){
        this.list = lista;
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                List<Customer> FilteredArrList = new ArrayList<Customer>();
                if (mOriginalValues  == null && list!=null)
                    mOriginalValues  = new ArrayList<Customer>(list); // guardar los datos originales en  mOriginalValues
                if (constraint == null || constraint.length() == 0)
                {
                    // setear los valores originales a returnar
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                }
                else
                {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++)
                    {
                        try
                        {
                            Customer data = mOriginalValues.get(i);
                            Object obj=data.isMatch(constraint);
                            if(Boolean.valueOf(obj.toString()))
                                FilteredArrList.add(data);
                            results.count = FilteredArrList.size();
                            results.values = FilteredArrList;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (results==null || (results!=null && results.values==null) || (results!=null &&  results.values!=null && ((List<Customer>)results.values).size()==0))
                    {
                        // setear los valores originales a returnar
                        results.count = mOriginalValues.size();
                        results.values = mOriginalValues;
                    }
                }

                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list =  (results!=null && results.values!=null)?(List<Customer>)results.values:new ArrayList<Customer>(); //contiene los datos filtrados
                notifyDataSetChanged();  //notificar al base adapter que hay nuevo valores que han sido filtrados
            }
        };
        return filter;
    }

    public void setPositionCache(int posicion){
        this.positionCache = posicion;
    }

    public int getPositionCache() {
        return this.positionCache;
    }
    public void setSelectedPosition(int pos) {
        selectedPos = pos;
    }
    public int getSelectedPosition(){
        return selectedPos;
    }

}
