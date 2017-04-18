package yio.io.sifaapp.fragment;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yio.io.sifaapp.BaseActivity;
import yio.io.sifaapp.Catalogos.CatalogoPresenter;
import yio.io.sifaapp.Catalogos.CatalogoPresenterIMP;
import yio.io.sifaapp.Catalogos.ICatalogoView;
import yio.io.sifaapp.EncargoListActivity;
import yio.io.sifaapp.R;
import yio.io.sifaapp.Venta.AppDialog;
import yio.io.sifaapp.Venta.IVenta;
import yio.io.sifaapp.Venta.VentaPresenterImplemt;
import yio.io.sifaapp.VentaListActivity;
import yio.io.sifaapp.adapter.CustomAdapter;
import yio.io.sifaapp.adapter.TypeLongClick;
import yio.io.sifaapp.adapter.productAdapter;
import yio.io.sifaapp.adapter.setOnLongClickListener;
import yio.io.sifaapp.dialogos.CustomerDialog;
import yio.io.sifaapp.dialogos.ProductoDialog;
import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Ciudad;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Descuento;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Producto;
import yio.io.sifaapp.model.Ruta;
import yio.io.sifaapp.model.modelSend.FacturaProformaDetalle;
import yio.io.sifaapp.model.modelSend.Venta;
import yio.io.sifaapp.sifacApplication;


/**
 * A simple {@link Fragment} subclass.
 */
public class NuevoVentaFragment extends Fragment implements IVenta, ICatalogoView, CalendarDatePickerDialogFragment.OnDateSetListener, setOnLongClickListener {


    @Bind(R.id.spinner_ruta)
    MaterialSpinner spinnerRuta;
    @Bind(R.id.button_calendar)
    ImageButton buttonCalendar;
    @Bind(R.id.txtfecha)
    TextView txtfecha;
    @Bind(R.id.button_cliente)
    ImageButton buttonCliente;
    @Bind(R.id.button_productos)
    ImageButton buttonProductos;
    @Bind(R.id.btnaceptar)
    Button btnaceptar;
    @Bind(R.id.btncancel)
    Button btncancel;
    @Bind(R.id.txtviewcustomer)
    TextView txtviewcustomer;
    @Bind(R.id.txtviewCD)
    TextView txtviewCD;
    @Bind(R.id.customerlinear)
    LinearLayout customerlinear;
    @Bind(R.id.ProductoRecyclerview)
    RecyclerView ProductoRecyclerview;
    @Bind(R.id.txtcustomercuota)
    TextView txtcustomercuota;
    @Bind(R.id.txtbuscar)
    TextView txtbuscar;
    @Bind(R.id.spinner_cuotas)
    MaterialSpinner spinnerCuotas;
    @Bind(R.id.spinner_plazos)
    MaterialSpinner spinnerPlazos;
    @Bind(R.id.txt_descuento)
    EditText txtDescuento;
    @Bind(R.id.radiogrupodescuento)
    RadioGroup radiodescuento;
    @Bind(R.id.radioSi)
    RadioButton radioSi;
    @Bind(R.id.radioNo)
    RadioButton radioNo;
    @Bind(R.id.txtcustomercuotatitle)
    TextView txtcustomercuotatitle;


    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private static final String TAG = NuevoVentaFragment.class.getSimpleName();
    View view = null;

    CatalogoPresenter catalogoPresenter;
    Customer customer = null;
    ProductoDialog dp = null;
    CustomerDialog dc = null;
    productAdapter adapter = null;
    Date fecha = null;
    List<Ruta> rutas;
    List<Producto> productos = new ArrayList<Producto>();
    List<Descuento> descuentos;
    List<Catalog> cuotas;
    List<Catalog> plazos;
    float montocondescuento = 0;
    float montosindescuento = 0;
    Catalog plazo;
    Catalog cuota;
    @Bind(R.id.EditText_observaciones)
    EditText EditTextObservaciones;
    Ruta r = null;
    @Bind(R.id.chknewsale)
    CheckBox chknewsale;

    public NuevoVentaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nuevo_venta, container, false);
        ButterKnife.bind(this, view);
        //setHasOptionsMenu(true);
        Init();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if (bundle.containsKey("Customer")) {
                customer = (Customer) bundle.getSerializable("Customer");
                SetCliente();
            }
        }
        return view; //new RecyclerView(container.getContext());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        catalogoPresenter = new CatalogoPresenterIMP(this);
        catalogoPresenter.onCreated();
        catalogoPresenter.getRutas();
        catalogoPresenter.getCuotas();
        catalogoPresenter.getPlazos();
        catalogoPresenter.fetchDescuentos();

        ProductoRecyclerview.setHasFixedSize(true);
        ProductoRecyclerview.setAdapter(adapter);
        ProductoRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        ((BaseActivity) getActivity()).getBottomBar().hide();
    }

    private void Init() {
        InitAdapter();
        radiodescuento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioSi) {
                    txtDescuento.setText("0");
                    txtDescuento.setEnabled(true);
                } else {
                    txtDescuento.setText("0");
                    txtDescuento.setEnabled(false);
                }
            }
        });

        txtDescuento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!TextUtils.isEmpty(txtDescuento.getText().toString()))
                    montocondescuento = montosindescuento - Float.valueOf(txtDescuento.getText().toString());
                    txtcustomercuota.setText(String.valueOf(montocondescuento));
            }
        });
        SetDate();
    }


    @Override
    public void onGetDescuento(List<Descuento> list) {

        CustomAdapter adapter = new CustomAdapter(getContext(), R.layout.spinner_rows, (ArrayList) list);
        // spinnerDescuento.setAdapter(adapter);

    }

    @Override
    public void onGetCuotas(List<Catalog> list) {

    }

    @Override
    public void onGetClientes() {

    }

    @Override
    public void onGetProductos() {

    }

    @Override
    public void onGetRutas() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void fetchPais(List<Pais> paises) {

    }

    @Override
    public void fechCiudades(List<Ciudad> ciudades) {

    }

    @Override
    public void fetchRutas(List<Ruta> list) {
        this.rutas = list;
        if(this.rutas.size()> 0 && this.rutas!=null){
        //  ArrayAdapter<Ruta> adapter = new ArrayAdapter<Ruta>(getContext(), android.R.layout.simple_dropdown_item_1line, rutas);
        if (spinnerRuta != null) {
            spinnerRuta.setItems(rutas);
            spinnerRuta.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                    r = rutas.get(position);

                }
            });
            spinnerRuta.setTextColor(getResources().getColor(R.color.colorPurple));
            spinnerRuta.setSelectedIndex(0);
            r = rutas.get(0);
        }
        }

    }

    @Override
    public void fetchGeneros(List<Catalog> generos) {

    }

    @Override
    public void fetchCuotas(final List<Catalog> cuotas) {
        this.cuotas = cuotas;
        if(this.cuotas!=null && this.cuotas.size() > 0) {
            if (spinnerCuotas != null) {
                spinnerCuotas.setItems(cuotas);
                spinnerCuotas.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        cuota = cuotas.get(position);
                    }
                });
                spinnerCuotas.setSelectedIndex(0);
                cuota = cuotas.get(0);
                spinnerCuotas.setTextColor(getResources().getColor(R.color.colorPurple));
            }
        }
    }

    @Override
    public void fetchPlazos(final List<Catalog> plazos) {
        this.plazos = plazos;
        if(this.plazos!=null && this.plazos.size()>0) {
            if (spinnerPlazos != null) {
                spinnerPlazos.setItems(plazos);
                spinnerPlazos.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        plazo = plazos.get(position);
                    }
                });
                spinnerPlazos.setTextColor(getResources().getColor(R.color.colorPurple));
                spinnerPlazos.setSelectedIndex(0);
                plazo = plazos.get(0);
            }
        }

    }

    @Override
    public void fetchDescuentos(List<Descuento> descuentos) {
        this.descuentos = descuentos;
    }

    @OnClick(R.id.button_calendar)
    public void initcalendar() {
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(this);
        cdp.show(getFragmentManager(), FRAG_TAG_DATE_PICKER);
    }


    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);
        fecha = c.getTime();
        txtfecha.setText(getString(R.string.calendar_date_picker_result_values, year, monthOfYear, dayOfMonth));
    }

    @Override
    public void onResume() {
        // Example of reattaching to the fragment
        super.onResume();
        CalendarDatePickerDialogFragment calendarDatePickerDialogFragment = (CalendarDatePickerDialogFragment) getFragmentManager().findFragmentByTag(FRAG_TAG_DATE_PICKER);
        if (calendarDatePickerDialogFragment != null) {
            calendarDatePickerDialogFragment.setOnDateSetListener(this);
        }
    }

    @OnClick(R.id.button_cliente)
    public void onClienteClick() {

        if(r!=null) {

            dc = new CustomerDialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen, this, r.getStbRutaID());
            Window window = dc.getWindow();
            window.setGravity(Gravity.CENTER);

            DisplayMetrics displaymetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int screenWidth = displaymetrics.widthPixels;
            int screenHeight = displaymetrics.heightPixels;

            window.setLayout(screenWidth - 40, screenHeight - 110);
            dc.show();
        }
        else{
            Toast.makeText(getContext(),"Ruta no seleccionada",Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.button_productos)
    public void onProductoClick() {
        dp = new ProductoDialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen, this);
        Window window = dp.getWindow();
        window.setGravity(Gravity.CENTER);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

        window.setLayout(screenWidth - 40, screenHeight - 110);
        dp.show();
    }

    public void SetCliente() {
        customerlinear.setVisibility(View.VISIBLE);
        txtviewcustomer.setText(customer.getNombreCliente());
        txtviewCD.setText(customer.getCedula());

    }

    @Override
    public void onLongButtonClick(TypeLongClick type, int position, Object row) {
        if (type == TypeLongClick.CUSTOMER) {
            dc.getAdapter().setSelectedPosition(position);
            customer = (Customer) row;
            Log.d(TAG, customer.getNombreCliente());
            SetCliente();
            dc.unbind();
            dc.hide();
        }
        if (type == TypeLongClick.PRODUCTO) {

            productos.add((Producto) row);
            Log.d(TAG, ((Producto) row).getNombre());
            dp.unbind();
            dp.hide();
            setdata(productos);
            montosindescuento += ((Producto) row).getPrecio_Credito();
            if (!TextUtils.isEmpty(txtDescuento.getText())) {
                txtcustomercuota.setText(String.valueOf(montosindescuento - Float.parseFloat(txtDescuento.getText().toString())));

            }
            else  txtcustomercuota.setText(String.valueOf(montosindescuento ));

        }
    }

    public void setdata(List<Producto> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    private void InitAdapter() {
        if (adapter == null) {
            adapter = new productAdapter(getContext());
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        catalogoPresenter.onDestroy();

    }
    @Override
    public void onStop() {
        //catalogoPresenter.onDestroy();
        super.onStop();
    }

    @OnClick(R.id.btnaceptar)
    public void OnRegistrar() {
        if (!save()) {
            AppDialog.showMessage(getActivity(), "", "Se ha Registrado Correctamente!",
                    AppDialog.DialogType.DIALOGO_ALERTA,
                    new AppDialog.OnButtonClickListener() {
                        @Override
                        public void onButtonClick(AlertDialog _dialog,
                                                  int actionId) {

                            if (AppDialog.OK_BUTTOM == actionId) {
                                _dialog.dismiss();
                                Intent i = new Intent(getActivity(), VentaListActivity.class);
                                startActivity(i);


                            }
                        }
                    });
        }
    }

    public boolean save() {
        Descuento discount = null;
        txtbuscar.setError(null);
        txtcustomercuota.setError(null);
        txtfecha.setError(null);
        txtDescuento.setError(null);

        String name = txtviewcustomer.getText().toString();
        float descuento = 0;

        boolean cancel = false;
        TextView focus = null;

        try
        {

           if(rutas.size()==0) {
                spinnerRuta.setError("No hay rutas Disponibles!");
                Toast.makeText(getContext(),"No hay rutas Disponibles!",Toast.LENGTH_SHORT).show();
                cancel = true;
            }

            else if (TextUtils.isEmpty(name)) {
                txtbuscar.setError(getString(R.string.message_vacio_Error));
                focus = txtbuscar;
                cancel = true;
            }
            else if (fecha == null) {
                txtfecha.setError(getString(R.string.message_vacio_Error));
                focus = txtfecha;
                cancel = true;
            }
            else if (montosindescuento <= 0) {
                txtcustomercuota.setError(getString(R.string.message_vacio_Error));
                focus = txtcustomercuota;
                cancel = true;
            }
            else if (radioSi.isChecked()) {

                descuento = Float.valueOf(txtDescuento.getText().toString());

                if (descuento == 0) {
                    txtDescuento.setError("Error!! Descuento no puede ser 0.");
                    focus = txtDescuento;
                    cancel = true;
                }
                else
                {
                    for (Descuento item : this.descuentos) {
                        if (item.getPlazoPago().equals(Float.valueOf(plazo.getCodigo()))) {
                            discount = item;
                            break;
                        }
                    }

                    if (discount == null) {
                        // ERROR NO SE ENCONTRO DESCUENTO
                        String error = String.format("No se encontró descuento asociado al plazo %s \nPosiblemente no se encuentra configurado.", plazo.getDescripcion());
                        txtDescuento.setError(error);
                        focus = txtDescuento;
                        cancel = true;
                        Toast toast = Toast.makeText( getContext(), error, Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else if (descuento > (montosindescuento * discount.getDescuentoMaximo())) {
                        String error = getString(R.string.message_descuento_maximo_Error);
                        txtDescuento.setError(error);
                        focus = txtDescuento;
                        cancel = true;
                        Toast toast = Toast.makeText( getContext(), error, Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
            if (cancel) {
                if(focus!=null)
                    focus.requestFocus();
            }
            else
            {

                    Venta venta = new Venta();
                    venta.setNuevaventa(chknewsale.isChecked());
                    venta.setCedula(customer.getCedula());
                    String fech = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
                    venta.setFecha(fech);
                    venta.setDescuento(descuento);
                    venta.setObjVendedorID(((sifacApplication) getActivity().getApplication()).getUsuario());
                    venta.setObservaciones(EditTextObservaciones.getText().toString());
                    venta.setPrima(0);
                    // buscar monto con descuento
                    montocondescuento = getMontocondescuento();

                    venta.setSaldo(montocondescuento);
                    venta.setSubtotal(montosindescuento);
                    venta.setTotal(montocondescuento);
                    venta.setObjTerminoPagoID(plazo.getStbValorCatalogoID());
                    if (discount != null)
                        venta.setObjDescuentoID(discount.getSccDescuentoID());
                    else
                        venta.setObjDescuentoID(null);
                    venta.setObjEstadoID(502);
                    venta.setOffline(true);
                    if (cuota != null)
                        venta.setObjModalidadPagoID(cuota.getStbValorCatalogoID());
                    venta.save();

                    for (Producto producto : productos) {
                        FacturaProformaDetalle detalle = new FacturaProformaDetalle();
                        detalle.setCedula(customer.getCedula());
                        detalle.setTotal(producto.getPrecio_Credito());
                        detalle.setPrecio(producto.getPrecio_Credito());
                        detalle.setCantidad(1);
                        detalle.setDescuento(0);
                        detalle.setObjSivProductoID(producto.getSivProductoID());
                        detalle.setSubtotal(producto.getPrecio_Credito());
                        detalle.save();
                    }
                    Log.d("NuevoVentaFragment", venta.toString());

            }
        }
        catch (Exception ex) {
            cancel = true;
            Log.d("Exception", ex.getMessage());
        }
        return cancel;

    }


    private void SetDate() {
        fecha = Calendar.getInstance().getTime();
        String day = new SimpleDateFormat("dd").format(fecha);    // always 2 digits
        String month = new SimpleDateFormat("MM").format(fecha);  // always 2 digits
        String year = new SimpleDateFormat("yyyy").format(fecha); // 4 digit year
        txtfecha.setText(getString(R.string.calendar_date_picker_result_values, year, month, day));
    }

    @OnClick(R.id.btncancel)
    public void cancel(){
        Intent intent = new Intent(getActivity(),VentaListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//end Bundle
        startActivity(intent);
        getActivity().finish();
    }


    private float getMontocondescuento(){

        if(!TextUtils.isEmpty(txtDescuento.getText().toString()))
            return montocondescuento = montosindescuento - Float.valueOf(txtDescuento.getText().toString());
        else
            return  montosindescuento;

    }
}
