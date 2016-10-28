package yio.io.sifaapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.R;
import yio.io.sifaapp.dialogos.CustomerDialog;
import yio.io.sifaapp.model.Customer;

/**
 * Created by JUANCARLOS on 15/09/2016.
 */
public class ClienteHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.txtviewcustomername)
    TextView txtviewcustomername;
    @Bind(R.id.txtviewcedula)
    TextView txtviewcedula;

    private  View view;

    public ClienteHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.view = itemView;
    }

    public  void setOnLongClickListener(final int position , final Customer customer , final setOnLongClickListener listener){
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongButtonClick(TypeLongClick.CUSTOMER,position,customer);
                return false;
            }
        });
    }

    /*
    public  void  setOnLongClickListener(final Customer customer , final setOnLongClickListener listener){
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongButtonClick(customer );
                return false;
            }
        });

    }
*/
    public View getView(){
        return  this.view;
    }
}
