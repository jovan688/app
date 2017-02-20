package yio.io.sifaapp.dialogos;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.R;
import yio.io.sifaapp.adapter.productAdapter;
import yio.io.sifaapp.adapter.setOnLongClickListener;
import yio.io.sifaapp.dialogos.product.productopresenter;
import yio.io.sifaapp.dialogos.product.productopresenterIMP;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.utils.DividerItemDecoration2;


/**
 * Created by JUANCARLOS on 15/09/2016.
 */
public class ProductoDialog extends Dialog  implements IDialogView{

    productAdapter adapter = null;
    @Bind(R.id.ProductRecyclerview)
    RecyclerView ProductRecyclerview;
    productopresenter presenter ;
    private setOnLongClickListener mButtonClickListener;
    //private long customerId;
    Context context;
    boolean activos;
    private  String cedula;

    public ProductoDialog(Context context, int themeResId , String cedula,boolean activos,setOnLongClickListener listener) {
        super(context, themeResId);
        this.cedula =cedula;
        this.context = context;
        mButtonClickListener = listener;
        this.activos = activos;
        init();
    }

    public ProductoDialog(Context context, int themeResId , String cedula,setOnLongClickListener listener) {
        super(context, themeResId);
        this.cedula =cedula;
        this.context = context;
        mButtonClickListener = listener;
        init();
    }

    public ProductoDialog(Context context, int themeResId , setOnLongClickListener listener) {
        super(context, themeResId);
        //this.customerId =customerId;
        this.context = context;
        mButtonClickListener = listener;

        init();
    }

    private void InitAdapter() {
        if (adapter == null) {
            adapter = new productAdapter(getContext(),mButtonClickListener);
        }
    }

    private void init() {
        setContentView(R.layout.producto_dialog);

        ButterKnife.bind(this, this);

        presenter = new productopresenterIMP(this);
        presenter.onCreated();

        InitAdapter();
        LinearLayout.LayoutParams layoutParams;

        LinearLayout viewroot = ((LinearLayout) findViewById(R.id.lmaincliente));
        LinearLayout llheader = ((LinearLayout) findViewById(R.id.llheader));
        LinearLayout llbody = ((LinearLayout) findViewById(R.id.llbody));
        viewroot.setBackgroundResource(R.drawable.bgdialog2);

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(2, 2, 1, 0);
        llheader.setLayoutParams(layoutParams);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(2, 0, 1, 1);
        llbody.setLayoutParams(layoutParams);

        EditText filterEditText = (EditText) findViewById(R.id.EditText_Client);
        filterEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (adapter != null)
                    adapter.getFilter().filter(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (adapter != null)
                    adapter.getFilter().filter(s.toString());
            }
        });

        LoadRecycler();

        if(cedula!=null )
            presenter.getProductsByCustomer(cedula , activos);
        else
            presenter.getAllProducts();

    }


    private void LoadRecycler() {
        ProductRecyclerview.setHasFixedSize(true);
        ProductRecyclerview.setAdapter(adapter);
        ProductRecyclerview.setLayoutManager(new LinearLayoutManager(getOwnerActivity()));
        ProductRecyclerview.addItemDecoration(new DividerItemDecoration2(context, DividerItemDecoration2.VERTICAL_LIST));
    }

    public void SetCustomerID(String cedula){
        this.cedula = cedula;
    }

    @Override
    public void onFetchData(List<Object> list) {
        List<Producto> l = (List)list;
        adapter.setData(l);
        adapter.notifyDataSetChanged();
    }


    public void unbind() {
        presenter.onDestroy();
    }


    @Override
    public void dismiss() {
        super.dismiss();
        presenter.onDestroy();
    }
}
