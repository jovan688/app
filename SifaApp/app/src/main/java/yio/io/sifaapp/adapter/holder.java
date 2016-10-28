package yio.io.sifaapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.R;

/**
 * Created by JUANCARLOS on 11/08/2016.
 */
public class holder  extends RecyclerView.ViewHolder  implements ItemTouchHelperViewHolder {

    @Bind(R.id.txtviewNum)
    Button txtviewNum;
    @Bind(R.id.txtviewcustomername)
    TextView txtviewcustomername;
    @Bind(R.id.txtviewamount)
    TextView txtviewamount;
    @Bind(R.id.txtviewdatepay)
    TextView txtviewdatepay;
    private  View view;

    public holder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.view = itemView;
    }


    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }
}
