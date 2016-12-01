package yio.io.sifaapp.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yio.io.sifaapp.BaseActivity;
import yio.io.sifaapp.EncargoListActivity;
import yio.io.sifaapp.EncargoNuevo.EncargoPresenter;
import yio.io.sifaapp.EncargoNuevo.EncargoPresenterIMP;
import yio.io.sifaapp.EncargoNuevo.IEncargoView;
import yio.io.sifaapp.EncargoNuevo.encargos.ItemEncargoAdapter;
import yio.io.sifaapp.R;
import yio.io.sifaapp.Venta.AppDialog;
import yio.io.sifaapp.VentaListActivity;
import yio.io.sifaapp.adapter.TypeLongClick;
import yio.io.sifaapp.adapter.productAdapter;
import yio.io.sifaapp.adapter.setOnLongClickListener;
import yio.io.sifaapp.dialogos.CustomerDialog;
import yio.io.sifaapp.dialogos.ProductoDialog;
import yio.io.sifaapp.model.Categoria;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Encargo;
import yio.io.sifaapp.model.EncargoDetalle;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.sifacApplication;
import yio.io.sifaapp.utils.DividerItemDecoration2;


/**
 * A simple {@link Fragment} subclass.
 */
public class NuevoEncargoFragment extends Fragment implements IEncargoView, setOnLongClickListener {


    EncargoPresenter presenter = null;
    CustomerDialog dc = null;
    Customer customer = null;
    private static final String TAG = NuevoEncargoFragment.class.getSimpleName();

    private List<Producto> myproductos = new ArrayList<Producto>();
    private List<Producto> productos;
    private List<Categoria> categorias;
    private Display display;
    private Categoria categoria = null;
    private ProductoDialog dp = null;
    private View view = null;
    private String productoname;
    private productAdapter adapter = null;
    boolean setted;
    ArrayList<String> encargos = new ArrayList<String>();
    private RecyclerView.Adapter adapterItem;
    private RecyclerView.LayoutManager layoutManager;

    @Bind(R.id.EncargoRecyclerview)
    RecyclerView EncargoRecyclerview;
    @Bind(R.id.txtname)
    EditText txtname;
    @Bind(R.id.txtapellido)
    TextView txtapellido;
    @Bind(R.id.txtcedula)
    TextView txtcedula;
    @Bind(R.id.txtproductoname)
    EditText txtproductoname;
    @Bind(R.id.txtObservaciones)
    EditText txtObservaciones;
    @Bind(R.id.btnsave)
    Button btnsave;
    @Bind(R.id.btncancel)
    Button btncancel;
    @Bind(R.id.button_cliente)
    ImageButton buttonCliente;
    @Bind(R.id.spinner_categoria)
    MaterialSpinner spinnerCategoria;
    @Bind(R.id.ProductRecyclerview)
    RecyclerView ProductRecyclerview;
    @Bind(R.id.button_add)
    ImageButton buttonAdd;

    public NuevoEncargoFragment() {
        presenter = new EncargoPresenterIMP(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_nuevo_encargo, container, false);
        ButterKnife.bind(this, view);
        presenter.onCreated();


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if (bundle.containsKey("Customer")) {
                customer = (Customer) bundle .getSerializable("Customer");
                SetCliente();
            }
        }

        WindowManager wm = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        InitAdapter();

        presenter.GetCatergorias();

        ((BaseActivity) getActivity()).getBottomBar().hide();


        layoutManager = new LinearLayoutManager(getActivity());
        EncargoRecyclerview.setLayoutManager(layoutManager);

        adapterItem = new ItemEncargoAdapter(encargos,getActivity());
        EncargoRecyclerview.setAdapter(adapterItem);

        txtproductoname.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //if (!setted)
                {
                    ProductRecyclerview.setVisibility(View.VISIBLE);
                    if (adapter != null) {
                        adapter.getFilter().filter(s.toString());
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (adapter != null) {
                    adapter.getFilter().filter(s.toString());
                }
                /*if (TextUtils.isEmpty(s)) {
                    setted = false;
                }*/
            }
        });

        LoadRecycler();
    }




    @Override
    public void enableInputs() {

    }

    @Override
    public void disableInputs() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void fetchCategorias(final List<Categoria> categorias) {
        this.categorias = categorias;

        spinnerCategoria.setItems(categorias);
        spinnerCategoria.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                categoria = categorias.get(position);
                presenter.GetProductos(categoria.getCategoriaID());
                Log.d("onItemSelected","categorias" );
            }
        });
        if(categoria == null) {
            spinnerCategoria.setSelectedIndex(0);
            categoria = categorias.get(0);
            presenter.GetProductos(categoria.getCategoriaID());
        }
        //presenter.onDestroy();
    }

    @Override
    public void fetchProductos(List<Producto> productos) {
        this.productos = productos;
        adapter.setData(productos);
        adapter.notifyDataSetChanged();
        ProductRecyclerview.setVisibility(View.GONE);
    }

    @Override
    public void fetchEncargos(List<Encargo> encargos) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
        ButterKnife.unbind(this);

        Log.d(TAG,"onDestroyView");
    }


    @OnClick(R.id.button_cliente)
    public void onClienteClick() {
        dc = new CustomerDialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen, this);
        Window window = dc.getWindow();
        window.setGravity(Gravity.CENTER);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

        window.setLayout(screenWidth - 40, screenHeight - 110);
        dc.show();
    }

    public void SetCliente() {
        txtname.setText(customer.getNombre1().toString() + " " + customer.getNombre2().toString());
        txtapellido.setText(customer.getApellido1().toString() + " " + customer.getApellido2().toString());
        txtcedula.setText(customer.getCedula());
    }


    private boolean save() {

        txtproductoname.setError(null);

        boolean cancel = false;
        EditText focus = null;
        /*
        if (TextUtils.isEmpty(productoname)) {
            txtproductoname.setError(getString(R.string.message_vacio_Error));
            cancel = true;
            focus = txtproductoname;
        }*/
        if (customer == null) {
            txtname.setError(getString(R.string.message_vacio_Error));
            cancel = true;
            focus = txtname;
        }
       // if(myproductos.size() == 0 && encargos.size() == 0  ){
        if( encargos.size() == 0  ){
            cancel = true;
            txtproductoname.setError(getString(R.string.message_producto_vacio));
            focus = txtproductoname;
        }
        if (cancel) {
            focus.requestFocus();
        }
        else
        {
            try
            {
                Encargo encargo = new Encargo();
                encargo.setCedula(txtcedula.getText().toString());
                encargo.setUsuarioCreacion(((sifacApplication) getActivity().getApplication()).getUsuario());
                encargo.setObjSrhEmpleadoID(((sifacApplication) getActivity().getApplication()).getUsuario());
                encargo.setFechaCreacion(Calendar.getInstance().getTime());
                encargo.setOffline(true);
                encargo.save();

                for (String item: encargos ) {
                    EncargoDetalle detalle = new EncargoDetalle();
                    detalle.setObservaciones(txtObservaciones.getText().toString());
                    detalle.setObjCategoriaID(categoria.getCategoriaID());
                    detalle.setEncargoid(encargo.id);
                    detalle.setNombre_Producto(item);
                    detalle.save();
                }
            }
            catch (Exception ex) {
                cancel = true;
                Log.d("Exception", ex.getMessage());
            }
        }
        return cancel;
    }

    @Override
    public void onLongButtonClick(TypeLongClick type, int position, Object row) {
        if (type == TypeLongClick.CUSTOMER) {
            dc.getAdapter().setSelectedPosition(position);
            customer = (Customer) row;
            dc.hide();
            SetCliente();
        }
        if (type == TypeLongClick.PRODUCTO) {
           // producto = (Producto) row;
            //productoname = producto.getNombre();
            myproductos.add((Producto) row);
            productoname= ((Producto) row).getNombre();
            ProductRecyclerview.setVisibility(View.GONE);
            setted = true;
            txtproductoname.setText(productoname);
        }
    }

    @OnClick(R.id.button_add)
    public void addproduct() {
        boolean cancel = false;
        txtproductoname.setError(null);

        if (TextUtils.isEmpty( txtproductoname.getText().toString())) {
            txtproductoname.setError(getString(R.string.message_vacio_Error));
            cancel = true;
        }
        if (cancel) {
            txtproductoname.requestFocus();
        } else {
            encargos.add(txtproductoname.getText().toString());
            Toast.makeText(getContext(), txtproductoname.getText().toString(), Toast.LENGTH_SHORT).show();
            txtproductoname.setText("");
            adapterItem = new ItemEncargoAdapter(encargos,getActivity());
            adapterItem.notifyDataSetChanged();
            EncargoRecyclerview.setAdapter(adapterItem);
            ProductRecyclerview.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btnsave)
    public void onSave(){
        if (!save()) {
            AppDialog.showMessage(getActivity(), "", "Se ha Registrado Correctamente!",
                    AppDialog.DialogType.DIALOGO_ALERTA,
                    new AppDialog.OnButtonClickListener() {
                        @Override
                        public void onButtonClick(AlertDialog _dialog, int actionId) {
                            if (AppDialog.OK_BUTTOM == actionId) {
                                _dialog.dismiss();
                                Intent i = new Intent(getActivity(), EncargoListActivity.class);
                                startActivity(i);
                            }
                        }
                    });

        }
    }


    private void InitAdapter() {
        if (adapter == null) {
            adapter = new productAdapter(getContext(), this);
        }
    }

    private void LoadRecycler() {
        ProductRecyclerview.setHasFixedSize(true);
        ProductRecyclerview.setAdapter(adapter);
        ProductRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        ProductRecyclerview.addItemDecoration(new DividerItemDecoration2(getContext(), DividerItemDecoration2.VERTICAL_LIST));
    }

    @OnClick(R.id.btncancel)
    public void cancel(){
        Intent intent = new Intent(getActivity(),EncargoListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//end Bundle
        startActivity(intent);
    }

}
