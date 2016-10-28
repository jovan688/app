package yio.io.sifaapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.R;

/**
 * Created by JUANCARLOS on 19/10/2016.
 */
public class ventaViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.txtviewIndex)
    Button txtviewIndex;
    @Bind(R.id.txtviewcustomername)
    TextView txtviewcustomername;
    @Bind(R.id.txtviewamount)
    TextView txtviewamount;
    @Bind(R.id.txtviewdatepay)
    TextView txtviewdatepay;


    private  View view;

    public ventaViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.view = itemView;
    }




}
