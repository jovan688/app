package yio.io.sifaapp.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yio.io.sifaapp.Cartera.CarteraListInteractorImplement;

import yio.io.sifaapp.Cartera.ClientePresenteImpl;
import yio.io.sifaapp.Cartera.ICarteraListInteractor;
import yio.io.sifaapp.Cartera.ICarteraListPresenter;
import yio.io.sifaapp.Cartera.IClientePresenter;
import yio.io.sifaapp.Cartera.IDetallaCartera;
import yio.io.sifaapp.Cartera.IDetallaCarteraView;
import yio.io.sifaapp.Cartera.productoPresenter;
import yio.io.sifaapp.Cartera.productoPresenterImp;
import yio.io.sifaapp.CarteraListActivity;
import yio.io.sifaapp.R;
import yio.io.sifaapp.Venta.AppDialog;
import yio.io.sifaapp.adapter.productAdapter;
import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.CarteraDetalle;
import yio.io.sifaapp.model.Cobro;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Producto;


public class DetalleClienteFragment extends Fragment implements IDetallaCartera {

    Cartera cartera = null;
    @Bind(R.id.txtviewcustomertitle)
    TextView txtviewcustomertitle;
    @Bind(R.id.txtviewcustomerCD)
    TextView txtviewcustomerCD;
    @Bind(R.id.txtcustomerAddr)
    TextView txtcustomerAddr;
    @Bind(R.id.txtcustomerdatetitle)
    TextView txtcustomerdatetitle;
    @Bind(R.id.txtcustomerdate)
    TextView txtcustomerdate;
    @Bind(R.id.txtcustomercuotatitle)
    TextView txtcustomercuotatitle;
    @Bind(R.id.txtcustomercuota)
    TextView txtcustomercuota;
    @Bind(R.id.txtcustomersaldotitle)
    TextView txtcustomersaldotitle;
    @Bind(R.id.txtcustomersaldo)
    TextView txtcustomersaldo;
    @Bind(R.id.btnabonar)
    Button btnabonar;
    @Bind(R.id.productosRecyclerView)
    RecyclerView productosRecyclerView;
    productAdapter adapter = null;
    productoPresenter presenter = null;
    IClientePresenter clientePresenter = null;
    List<CarteraDetalle> detalles = null;
    @Bind(R.id.txtviewtelefono)
    TextView txtviewtelefono;
    Customer customer;
    @Bind(R.id.btnnoabonar)
    Button btnnoabonar;
    @Bind(R.id.txtreferencia)
    EditText txtreferencia;
    @Bind(R.id.btnreferencia)
    Button btnreferencia;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    Long CarteraID;
    private static final String TAG = DetalleClienteFragment.class.getSimpleName();
    @Bind(R.id.txtorden)
    EditText txtorden;
    int oldorden =0;
    String oldreferencia = "";


    public DetalleClienteFragment() {
        this.presenter = new productoPresenterImp(this);
        this.clientePresenter = new ClientePresenteImpl(this);
        this.cartera = null;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            CarteraID = (Long) bundle.getSerializable("CarteraID");
            cartera = new Select().from(Cartera.class).where(String.format("id=%d", CarteraID)).querySingle();
            oldorden= cartera.getOrdenCobro();

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_cliente, container, false);
        ButterKnife.bind(this, view);

        presenter.onCreated();
        clientePresenter.onCreated();

        initAdapter();
        InitRecycler();
        Init();
        clientePresenter.getCliente(cartera.getClienteID());
        presenter.getPrpductosbyCustomerId(cartera.getClienteID());
        //

        return view;
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        clientePresenter.onDestroy();
        super.onDestroy();
    }



    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btnabonar)
    public void onclick() {
        AppDialog.showMessage(getContext(), "", AppDialog.DialogType.DIALOGO_ABONO, new AppDialog.OnAbonoClickListener() {
            @Override
            public void onButtonClick(int actionId, Object o) {
                if (!save((Float) o)) {
                    presenter.onDestroy();
                    clientePresenter.onDestroy();
                    Intent i = new Intent(getActivity(), CarteraListActivity.class);
                    startActivity(i);

                }

            }
        });
        AppDialog.setRadiocuota(cartera.getMontoCuota().toString());

        /*
        if (cartera.getCobrado() == false) {
            AppDialog.showMessage(getContext(), "", AppDialog.DialogType.DIALOGO_ABONO, new AppDialog.OnAbonoClickListener() {
                @Override
                public void onButtonClick(int actionId, Object o) {

                    save((Float)o);

                    btnabonar.setEnabled(false);
                    btnnoabonar.setEnabled(false);

                }
            });
            AppDialog.setRadiocuota(cartera.getMontoCuota().toString());
        }
        else {
            showmessage("Ya se ha Cobrado.");
            //btnnoabonar.setEnabled(false);
        }
        */
    }

    @OnClick(R.id.btnnoabonar)
    public void onclickNoAbonar() {

        if (!save(0f)) {
            AppDialog.showMessage(getActivity(), "", "Se ha Registrado Correctamente!",
                    AppDialog.DialogType.DIALOGO_ALERTA,
                    new AppDialog.OnButtonClickListener() {
                        @Override
                        public void onButtonClick(AlertDialog _dialog, int actionId) {
                            if (AppDialog.OK_BUTTOM == actionId) {
                                _dialog.dismiss();
                                presenter.onDestroy();
                                clientePresenter.onDestroy();
                                Intent i = new Intent(getActivity(), CarteraListActivity.class);
                                startActivity(i);
                            }
                        }
                    });
        }

        /*
        if (cartera.getCobrado() == false) {

            Cobro cobro = new Cobro();
            cobro.setAbono(false);
            cobro.setCancelo(false);
            cobro.setFechaAbono(Calendar.getInstance().getTime());
            cobro.setMontoAbonado(0f);
            cobro.setMotivoNoAbono("");
            cobro.setObjCobradorID(cartera.getOjbCobradorID());
            cobro.setObjSccClienteID(cartera.getClienteID());
            cobro.setObjSccCuentaID(Integer.parseInt(cartera.getSccCuentaID()));
            cobro.setObjStbRutaID(cartera.getStbRutaID());
            cobro.setSaldo(cartera.getSaldo());
            cobro.setUsuarioCreacion(cartera.getOjbCobradorID());
            cobro.setOffline(true);
            cobro.save();

            cartera.setCobrado(true);
            cartera.save();
            btnabonar.setEnabled(false);
            btnnoabonar.setEnabled(false);

            showmessage("Se ha guardado correctamente.");
        } else {
            showmessage("Ya se ha Cobrado.");
            btnnoabonar.setEnabled(false);
        }
*/

    }

    public boolean save(Float amt) {
        boolean cancelar = false;

        if (cartera.getCobrado() == false) {


            Cobro cobro = new Cobro();
            if (amt == 0)
                cobro.setAbono(false);
            else
                cobro.setAbono(true);

            if (amt == cartera.getSaldo())
                cobro.setCancelo(true);
            else
                cobro.setCancelo(false);
            cobro.setFechaAbono(Calendar.getInstance().getTime());
            cobro.setMontoAbonado(amt);
            cobro.setMotivoNoAbono("");
            cobro.setObjCobradorID(cartera.getOjbCobradorID());
            cobro.setCedula(cartera.getCedula());
            cobro.setObjSccCuentaID(Integer.parseInt(cartera.getSccCuentaID()));
            cobro.setObjStbRutaID(cartera.getStbRutaID());
            cobro.setSaldo(cartera.getSaldo());
            cobro.setUsuarioCreacion(cartera.getOjbCobradorID());
            cobro.setOffline(true);
            cobro.save();

            cartera.setCobrado(true);
            cartera.save();
            Log.d(TAG, "Se ha Guardado el Cobro");

        } else {
            cancelar = true;
        }
        return cancelar;
    }

    private void Init() {
        if (cartera != null) {
            txtviewcustomertitle.setText(cartera.getNombreCompleto());
            txtviewcustomerCD.setText(cartera.getCedula());
            txtcustomerAddr.setText(cartera.getDireccion());
            txtcustomerdate.setText(cartera.getFechaAbono());
            txtcustomercuota.setText(cartera.getMontoCuota().toString());
            txtcustomersaldo.setText(cartera.getSaldo().toString());
            txtviewtelefono.setText("");
        }

        ((CarteraListActivity) getActivity()).getBottomBar().hide();

        if (cartera.getCobrado() == true) {
            btnabonar.setEnabled(false);
            btnabonar.setAlpha((float) 0.5);
            btnnoabonar.setEnabled(false);
            btnabonar.setAlpha((float) 0.5);
        }
        if(cartera!=null){
            txtorden.setText(String.valueOf(cartera.getOrdenCobro()));
        }
    }

    private void initAdapter() {
        if (adapter == null) {
            adapter = new productAdapter(getContext());
        }
    }

    private void InitRecycler() {
        productosRecyclerView.setHasFixedSize(true);
        productosRecyclerView.setAdapter(adapter);
        productosRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onSetCarteraDetalle(List<Producto> detalles) {
        adapter.setData(detalles);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSetCliente(Customer customer) {
        this.customer = customer;
        if (customer != null) {
            txtviewtelefono.setText(customer.getTelefonos());
            txtreferencia.setText(customer.getReferencia());
            //oldorden = customer.getOrdenCobro();
            oldreferencia =customer.getReferencia();
          /*  if(customer.getOrdenCobro()!=0)
                txtorden.setText(customer.getOrdenCobro().toString());
            else
                txtorden.setText(customer.getOrdenCobro().toString()); */

        }
        //clientePresenter.onDestroy();
    }


    @Override
    public void onResume() {
        super.onResume();
        //clientePresenter.onDestroy();
        //presenter.onDestroy();
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btnreferencia)
    public void save_referencia() {
        if (!savereferencia()) {
            int nuevo_orden = Integer.parseInt(txtorden.getText().toString());
            if(nuevo_orden!=oldorden) {
                clientePresenter.executeUpdateOrden(oldorden, nuevo_orden, cartera);
            }
        }
    }

    @Override
    public void UpdatedOrden(boolean value) {
        AppDialog.showMessage(getActivity(), "", "Se ha Guardado Correctamente!",
                AppDialog.DialogType.DIALOGO_ALERTA,
                new AppDialog.OnButtonClickListener() {
                    @Override
                    public void onButtonClick(AlertDialog _dialog, int actionId) {
                        if (AppDialog.OK_BUTTOM == actionId) {
                            _dialog.dismiss();
                            presenter.onDestroy();
                            clientePresenter.onDestroy();
                            Intent i = new Intent(getActivity(), CarteraListActivity.class);
                            startActivity(i);
                        }
                    }
                });
    }

    @Override
    public void onMessage(String message) {
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();    }


    private boolean savereferencia() {
        boolean cancelar = false;
        try
        {
            String referencia = String.valueOf(txtreferencia.getText());

            if(!referencia.isEmpty() && !referencia.equals(oldreferencia)) {
                customer.setReferencia(referencia);
                customer.setOfflineReferencia(true);
            }

            int nuevo_orden = Integer.parseInt(txtorden.getText().toString());

            if(nuevo_orden!=oldorden) {
                customer.setNewOrden(true);
            }
            customer.save();

        } catch (Exception ex) {
            cancelar = true;
            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
        }
        return cancelar;
    }

}
