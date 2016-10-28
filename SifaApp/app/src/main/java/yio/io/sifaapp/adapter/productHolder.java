package yio.io.sifaapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.R;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Producto;

/**
 * Created by JUANCARLOS on 11/09/2016.
 */
public class productHolder extends RecyclerView.ViewHolder {

    private  View view;
    @Bind(R.id.txtproductoname)
    TextView txtproductoname;
    @Bind(R.id.txtprecioproducto)
    TextView txtprecioproducto;
    @Bind(R.id.txtproductodescription)
    TextView txtproductodescription;

    public productHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.view = itemView;
    }
    public View getView(){
        return  this.view;
    }


    public  void setOnLongClickListener(final int position , final Producto producto , final setOnLongClickListener listener){
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongButtonClick(TypeLongClick.PRODUCTO,position,producto);
                return false;
            }
        });
    }
}
