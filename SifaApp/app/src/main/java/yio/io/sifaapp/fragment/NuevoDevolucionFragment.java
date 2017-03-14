package yio.io.sifaapp.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yio.io.sifaapp.BaseActivity;
import yio.io.sifaapp.Devolucion.IDevolucionListPresenter;
import yio.io.sifaapp.Devolucion.IDevolucionListPresenterImpl;
import yio.io.sifaapp.Devolucion.IDevolucionView;
import yio.io.sifaapp.DevolucionListActivity;
import yio.io.sifaapp.R;
import yio.io.sifaapp.Venta.AppDialog;
import yio.io.sifaapp.adapter.TypeLongClick;
import yio.io.sifaapp.adapter.productAdapter;
import yio.io.sifaapp.adapter.setOnLongClickListener;
import yio.io.sifaapp.dialogos.CustomerDialog;
import yio.io.sifaapp.dialogos.ProductoDialog;
import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Devolucion;
import yio.io.sifaapp.model.DevolucionProductos;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.sifacApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class NuevoDevolucionFragment extends Fragment implements IDevolucionView, setOnLongClickListener {


    @Bind(R.id.txtbuscar)
    TextView txtbuscar;
    @Bind(R.id.button)
    ImageButton button;
    View view = null;
    private static final String TAG = NuevoDevolucionFragment.class.getSimpleName();
    CustomerDialog dc = null;
    ProductoDialog dp = null;
    Customer customer = null;
    @Bind(R.id.txtname)
    TextView txtname;
    @Bind(R.id.txtapellido)
    TextView txtapellido;
    @Bind(R.id.txtcedula)
    TextView txtcedula;


    List<Producto> productos = new ArrayList<Producto>();
    productAdapter adapter = null;
    @Bind(R.id.FacturaRecyclerview)
    RecyclerView FacturaRecyclerview;
    @Bind(R.id.btnsave)
    Button btnsave;
    @Bind(R.id.btnobservaciones)
    EditText btnobservaciones;
    IDevolucionListPresenter presenter;
    @Bind(R.id.btncancel)
    Button btncancel;
    @Bind(R.id.buttonproducto)
    ImageButton buttonproducto;
    @Bind(R.id.scrollView3)
    ScrollView scrollView3;
    private Display display;
    List<DevolucionProductos> detalles = new ArrayList<DevolucionProductos>();

    public NuevoDevolucionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_nuevo_devolucion, container, false);
        ButterKnife.bind(this, view);
        WindowManager wm = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        InitAdapter();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FacturaRecyclerview.setHasFixedSize(true);
        FacturaRecyclerview.setAdapter(adapter);
        FacturaRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter = new IDevolucionListPresenterImpl(this);
        presenter.onCreated();
        ((BaseActivity) getActivity()).getBottomBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStop() {
        dc.dismiss();
        presenter.onDestroy();
        super.onStop();
        Log.d(TAG, "onStop");

    }

    @OnClick(R.id.button)
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


    @OnClick(R.id.buttonproducto)
    public void onProductoClick() {

        if (customer == null) {
            showmessage("Seleccione un Cliente");
            return;
        }
        dp = new ProductoDialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen, customer.getCedula(), false, this);
        Window window = dp.getWindow();
        window.setGravity(Gravity.CENTER);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

        window.setLayout(screenWidth - 40, screenHeight - 110);
        dp.show();
    }

    private void InitAdapter() {
        if (adapter == null) {
            adapter = new productAdapter(getContext());
        }
    }

    public void SetCliente() {
        txtname.setText(customer.getNombre1().toString() + " " + customer.getNombre2().toString());
        txtapellido.setText(customer.getApellido1().toString() + " " + customer.getApellido2().toString());
        txtcedula.setText(customer.getCedula());
    }

    @Override
    public void onLongButtonClick(TypeLongClick type, int position, Object row) {
        if (type == TypeLongClick.CUSTOMER) {
            dc.getAdapter().setSelectedPosition(position);
            customer = (Customer) row;
            Log.d(TAG, customer.getNombreCliente());
            dc.unbind();
            dc.hide();
            SetCliente();
            presenter.obtenerDetalle(customer.getClienteID());
        }
        if (type == TypeLongClick.PRODUCTO) {
            productos.clear();
            productos.add((Producto) row);
            Log.d(TAG, ((Producto) row).getNombre());
            setdata(productos);
            dp.unbind();
            dp.hide();
        }
    }

    public void setdata(List<Producto> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnsave)
    public void save() {
        if (!savedate()) {
            AppDialog.showMessage(getActivity(), "", "Se ha Registrado Correctamente!",
                    AppDialog.DialogType.DIALOGO_ALERTA,
                    new AppDialog.OnButtonClickListener() {
                        @Override
                        public void onButtonClick(AlertDialog _dialog,
                                                  int actionId) {

                            if (AppDialog.OK_BUTTOM == actionId) {
                                _dialog.dismiss();
                                Intent i = new Intent(getActivity(), DevolucionListActivity.class);
                                startActivity(i);


                            }
                        }
                    });
        }
    }

    private boolean savedate() {

        boolean cancel = false;
        TextView focus = null;
        try
        {
            if (customer == null) {
                showmessage("Seleccione un Cliente");
                cancel = true;
            }
            if (productos.size() == 0) {
                showmessage("Seleccione al menos un Producto.");
                cancel = true;
            }

            Cartera cartera = (Cartera) new Select().from(Cartera.class)
                    .where(String.format("Cedula='%s'", customer.getCedula()))
                    .querySingle();

            if (cartera == null) {
                showmessage("No se encontró registro de Cartera!");
                cancel = true;
            }
            if (cancel == false) {


                Devolucion devolucion = new Devolucion();
                devolucion.setCedula(customer.getCedula());
                devolucion.setObjSccCuentaID(Integer.parseInt(cartera.getSccCuentaID())); //Integer.parseInt((((sifacApplication) getActivity().getApplication()).getSsgCuentaID())));
                devolucion.setObjStbRutaID(customer.getStbRutaID());
                devolucion.setObjSivProductoID(productos.get(0).getSivProductoID());
                devolucion.setTotalDevolucion(productos.get(0).getPrecio_Credito());

                if (detalles.size() > 0)
                    devolucion.setObjSfaFacturaID(detalles.get(0).getObjSfaFacturaID());

                if (!TextUtils.isEmpty(btnobservaciones.getText())) {
                    devolucion.setRazonDevolucion(btnobservaciones.getText().toString());
                }
                devolucion.setObjVendedorID(((sifacApplication) getActivity().getApplication()).getUsuario());
                devolucion.setUsuarioCreacion(((sifacApplication) getActivity().getApplication()).getUsuario());
                String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                devolucion.setFecha(fecha);
                devolucion.setOffline(true);
                devolucion.save();

            }

        }
        catch (Exception ex) {
                cancel = true;
                Log.d("Exception", ex.getMessage());
                showmessage(ex.getMessage());
            }


        return cancel;
    }

    public void showmessage(final String message) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppDialog.showMessage(getContext(), "", message,
                        AppDialog.DialogType.DIALOGO_ALERTA,
                        new AppDialog.OnButtonClickListener() {
                            @Override
                            public void onButtonClick(AlertDialog _dialog,
                                                      int actionId) {

                                if (AppDialog.OK_BUTTOM == actionId) {
                                    _dialog.dismiss();
                                }
                            }
                        });
            }
        });
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
    public void onDataFetch(List<Devolucion> devolucions) {

    }

    @Override
    public void onCustomer(Customer c) {

    }

    @Override
    public void obtenerDetalle(List<DevolucionProductos> detalle) {
        detalles = detalle;
    }

    @OnClick(R.id.btncancel)
    public void cancel() {
        Intent intent = new Intent(getActivity(), DevolucionListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//end Bundle
        startActivity(intent);
    }
}
