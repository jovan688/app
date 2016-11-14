package yio.io.sifaapp.dialogos;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.R;
import yio.io.sifaapp.adapter.ClienteAdapter;
import yio.io.sifaapp.adapter.setOnLongClickListener;
import yio.io.sifaapp.dialogos.customer.CustomerDialogPresenter;
import yio.io.sifaapp.dialogos.customer.CustomerDialogPresenterImp;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.utils.DividerItemDecoration2;

/**
 * Created by JUANCARLOS on 15/09/2016.
 */
public class CustomerDialog extends Dialog implements IDialogView {

    private static final String TAG = CustomerDialog.class.getSimpleName();
    ClienteAdapter adapter = null;
    @Bind(R.id.CustomerRecyclerview)
    RecyclerView CustomerRecyclerview;
    public ProgressDialog pd;
    @Bind(R.id.llbody)
    LinearLayout llbody;
    CustomerDialogPresenter presenter = null;
    private setOnLongClickListener mButtonClickListener;
    Context context;
    int rutaid;

    public CustomerDialog(Context context, int themeResId, setOnLongClickListener listener , int rutaid) {
        super(context, themeResId);
        this.rutaid = rutaid;
        setContentView(R.layout.customer_dialog);


        ButterKnife.bind(this, this);

        presenter = new CustomerDialogPresenterImp(this);
        presenter.onCreated();

        mButtonClickListener = listener;
        init();
    }

    public CustomerDialog(Context context, int themeResId, setOnLongClickListener listener) {
        super(context, themeResId);
        this.context = context;
        setContentView(R.layout.customer_dialog);
        // pd = ProgressDialog.show(getContext(), "Espere por favor", "Trayendo Info...", true, false);


        ButterKnife.bind(this);

        presenter = new CustomerDialogPresenterImp(this);
        presenter.onCreated();

        mButtonClickListener = listener;
        init();
    }


    private void init() {
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

        if(rutaid !=0)
            presenter.onFetchData(rutaid);
        else
            presenter.onFetchData();

    }

    private void InitAdapter() {
        if (adapter == null) {
            adapter = new ClienteAdapter(getContext(), mButtonClickListener);
        }
    }


    @Override
    public void onFetchData(List<Object> list) {
        //pd.hide();
        List<Customer> l = (List) list;
        adapter.setData(l);
        adapter.notifyDataSetChanged();
        Log.d(TAG, "onFetchData > CLIENTES");
        //presenter.onDestroy();

    }

    private void LoadRecycler() {
        CustomerRecyclerview.setHasFixedSize(true);
        CustomerRecyclerview.setAdapter(adapter);
        CustomerRecyclerview.setLayoutManager(new LinearLayoutManager(getOwnerActivity()));
       // CustomerRecyclerview.addItemDecoration(new DividerItemDecoration2(context, DividerItemDecoration2.VERTICAL_LIST));
    }

    public ClienteAdapter getAdapter() {
        return adapter;
    }

    public void unbind() {
        presenter.onDestroy();
    }


}