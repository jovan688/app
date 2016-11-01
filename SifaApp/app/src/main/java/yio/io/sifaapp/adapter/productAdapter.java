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
import yio.io.sifaapp.model.Devolucion;
import yio.io.sifaapp.model.Producto;

/**
 * Created by JUANCARLOS on 11/09/2016.
 */
public class productAdapter extends RecyclerView.Adapter<productHolder> implements Filterable {

    List<Producto> list = new ArrayList<Producto>();

    private setOnLongClickListener mButtonClickListener;
    Context context;
    private List<Producto> mOriginalValues;
    private int selectedPos = 0;
    private int positionCache;

    public productAdapter(Context context) {
        this.context = context;
    }

    public productAdapter(Context context, setOnLongClickListener listener) {
        mButtonClickListener = listener;
        this.context = context;
    }


    @Override
    public productHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productrow, parent, false);
        productHolder itemViewHolder = new productHolder(view);

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(productHolder holder, int position) {
        if(list.get(position) instanceof Producto) {
            Producto p = list.get(position);
            if (holder != null) {
                holder.txtprecioproducto.setText(p.getPrecio_Credito().toString());
                holder.txtproductoname.setText(p.getNombre());
                holder.txtproductodescription.setText(p.getDescripcion());
            }
            if (mButtonClickListener != null)
                holder.setOnLongClickListener(position, p, mButtonClickListener);

            if (holder.getView() != null) {
                if (getSelectedPosition() == position) {
                    holder.getView().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.action_item_selected));
                } else {
                    holder.getView().setBackgroundResource(android.R.color.transparent);
                }
            }
        }
    }

    public productAdapter(List<Producto> list) {
        this.list = list;
    }

    public void setData(List<Producto> lista) {
       // if (list != null ) this.list.clear();
        this.list = lista;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                List<Producto> FilteredArrList = new ArrayList<Producto>();

               // => if (mOriginalValues == null && list != null)
                if (list != null)
                    mOriginalValues = new ArrayList<Producto>(list); // guardar los datos originales en  mOriginalValues

                if (constraint == null || constraint.length() == 0) {
                    // setear los valores originales a returnar
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        try {
                            Producto data = mOriginalValues.get(i);
                            Object obj = data.isMatch(constraint);
                            if (Boolean.valueOf(obj.toString()))
                                FilteredArrList.add(data);
                            results.count = FilteredArrList.size();
                            results.values = FilteredArrList;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (results == null || (results != null && results.values == null) || (results != null && results.values != null && ((List<Producto>) results.values).size() == 0)) {
                        // setear los valores originales a returnar
                        results.count = mOriginalValues.size();
                        results.values = mOriginalValues;
                    }
                }

                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (results != null && results.values != null) ? (List<Producto>) results.values : new ArrayList<Producto>(); //contiene los datos filtrados
                notifyDataSetChanged();  //notificar al base adapter que hay nuevo valores que han sido filtrados
            }
        };
        return filter;
    }

    public int getPositionCache() {
        return this.positionCache;
    }

    public void setSelectedPosition(int pos) {
        selectedPos = pos;
    }

    public int getSelectedPosition() {
        return selectedPos;
    }
}
