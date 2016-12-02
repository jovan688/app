package yio.io.sifaapp.fragment;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yio.io.sifaapp.BaseActivity;
import yio.io.sifaapp.CarteraListActivity;
import yio.io.sifaapp.ClienteNuevo.ClientePresenter;
import yio.io.sifaapp.ClienteNuevo.ClientePresenterIMP;
import yio.io.sifaapp.ClienteNuevo.IClienteView;
import yio.io.sifaapp.R;
import yio.io.sifaapp.Venta.AppDialog;
import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Ciudad;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Ruta;
import yio.io.sifaapp.sifacApplication;
import yio.io.sifaapp.utils.ValidarCedula;


/**
 * A simple {@link Fragment} subclass.
 */
public class NuevoClienteFragment extends Fragment implements IClienteView {

    Ruta ruta;
    Ciudad ciudad;
    Pais pais;
    ClientePresenter presenter;
    List<Ciudad> listciudades;
    List<Ruta> listrutas;
    List<Catalog> generos;
    List<Pais> listpaises;
    Customer customer = null;
    @Bind(R.id.editname)
    EditText editname;
    @Bind(R.id.editlastname)
    EditText editlastname;
    @Bind(R.id.editcid)
    EditText editcid;
    @Bind(R.id.female)
    RadioButton female;
    @Bind(R.id.generoradiogroup)
    RadioGroup generoradiogroup;
    @Bind(R.id.editphone)
    EditText editphone;
    @Bind(R.id.editAddress)
    EditText editAddress;
    @Bind(R.id.btnsave_nuevo_encargo)
    Button btnsaveNuevoEncargo;
    @Bind(R.id.btnsave)
    Button btnsave;
    @Bind(R.id.btncancelar)
    Button btncancelar;
    @Bind(R.id.spinner_country)
    MaterialSpinner spinnerCountry;
    @Bind(R.id.spinner_city)
    MaterialSpinner spinnerCity;
    @Bind(R.id.spinner_ruta)
    MaterialSpinner spinnerRuta;
    @Bind(R.id.btnsave_nueva_venta)
    Button btnsaveNuevaVenta;
    @Bind(R.id.male)
    RadioButton male;
    private final String TAG = this.getClass().getSimpleName();
    View view = null;

    public NuevoClienteFragment() {
        presenter = new ClientePresenterIMP(this);
        customer = new Customer();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nuevo_cliente, container, false);
        ButterKnife.bind(this, view);
        presenter.onCreated();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(presenter != null) {
            presenter.getCiudades();
            presenter.getRutas();
            presenter.getGenero();
            presenter.getpais();
        }

        ((BaseActivity) getActivity()).getBottomBar().hide();

        generoradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male) {
                    for (Catalog catalog : generos) {
                        if (catalog.getCodigo().equals("M")) {
                            customer.setGenero(catalog.getNombre());
                            customer.setObjGeneroID(catalog.getStbValorCatalogoID());
                            break;
                        }
                    }
                } else {
                    for (Catalog catalog : generos) {
                        if (catalog.getCodigo().equals("F")) {
                            customer.setGenero(catalog.getNombre());
                            customer.setObjGeneroID(catalog.getStbValorCatalogoID());
                            break;
                        }
                    }
                }
            }
        });
        male.setChecked(true);
    }

    @Override
    public void fetchPais(List<Pais> paises) {
        Log.d(TAG,"fechCiudades ");
        this.listpaises = paises;
        spinnerCountry.setItems(paises);
        spinnerCountry.setTextColor(getResources().getColor(R.color.colorPurple));
        spinnerCountry.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                pais = listpaises.get(position);
            }
        });
        spinnerCountry.setSelectedIndex(0);
        pais = listpaises.get(0);
    }


    @Override
    public void fechCiudades(List<Ciudad> ciudades) {
        Log.d(TAG,"fechCiudades ");
        this.listciudades = ciudades;
        spinnerCity.setItems(ciudades);
        spinnerCity.setTextColor(getResources().getColor(R.color.colorPurple));
        spinnerCity.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                ciudad = listciudades.get(position);
            }
        });
        spinnerCity.setSelectedIndex(0);
        ciudad = listciudades.get(0);

    }

    @Override
    public void fetchRutas(List<Ruta> rutas) {
        Log.d(TAG,"fetchRutas ");
        this.listrutas = rutas;

        //ArrayAdapter<Ruta> adapter = new ArrayAdapter<Ruta>(getContext(), android.R.layout.simple_spinner_item, rutas);
        spinnerRuta.setItems(rutas);
        spinnerRuta.setTextColor(getResources().getColor(R.color.colorPurple));
        spinnerRuta.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                ruta = listrutas.get(position);
            }
        });
        spinnerRuta.setSelectedIndex(0);
        ruta = listrutas.get(0);

    }

    @Override
    public void fetchGeneros(List<Catalog> generos) {
        Log.d(TAG,"fetchGeneros ");
        this.generos = generos;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btnsave)
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
                                Intent i = new Intent(getActivity() , CarteraListActivity.class);
                                startActivity(i);
                            }
                        }
                    });
        }
    }

    @OnClick(R.id.btnsave_nuevo_encargo)
    public void onRegistrar_Encargo() {
        if (!save()) {
            Bundle data = new Bundle();
            data.putSerializable("Customer", customer);
            Fragment fragment = new NuevoEncargoFragment();
            fragment.setArguments(data);
            FragmentManager manager = getActivity().getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            ((BaseActivity) getActivity()).getSupportActionBar().setTitle("Nuevo Encargo");
        }
    }

    @OnClick(R.id.btnsave_nueva_venta)
    public void onRegistrar_Venta() {
        if (!save()) {
            Bundle data = new Bundle();
            data.putSerializable("Customer", customer);
            Fragment fragment = new NuevoVentaFragment();
            fragment.setArguments(data);
            FragmentManager manager = getActivity().getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            ((BaseActivity) getActivity()).getSupportActionBar().setTitle("Nuevo Venta");
        }
    }

    private boolean save() {
        //customer = new Customer();
        String name = editname.getText().toString();
        String lastname = editlastname.getText().toString();
        String cid = editcid.getText().toString();
        String address = editAddress.getText().toString();
        String phone = editphone.toString();


        editname.setError(null);
        editlastname.setError(null);
        editcid.setError(null);
        editAddress.setError(null);
        editphone.setError(null);


        boolean cancel = false;
        EditText focus = null;

        if (TextUtils.isEmpty(name)) {
            editname.setError(getString(R.string.message_vacio_Error));
            focus = editname;
            cancel = true;
        } else if (TextUtils.isEmpty(lastname)) {
            editlastname.setError(getString(R.string.message_vacio_Error));
            focus = editlastname;
            cancel = true;
        } else if (TextUtils.isEmpty(cid)) {
            editcid.setError(getString(R.string.message_vacio_Error));
            focus = editcid;
            cancel = true;
        } else if (TextUtils.isEmpty(address)) {
            editAddress.setError(getString(R.string.message_vacio_Error));
            focus = editAddress;
            cancel = true;
        } else if (TextUtils.isEmpty(phone)) {
            editphone.setError(getString(R.string.message_vacio_Error));
            focus = editphone;
            cancel = true;
        } else if (!TextUtils.isEmpty(cid)) {
            ValidarCedula validarCedula = new ValidarCedula();
            validarCedula.setCedula(cid);
            if (!validarCedula.isCedulaValida()) {
                editcid.setError("Cédula Inválida");
                focus = editcid;
                cancel = true;
            }
        }
        if (cancel) {
            focus.requestFocus();
        } else {

            customer.setNombre1(editname.getText().toString());
            customer.setNombre2("");
            customer.setApellido1(editlastname.getText().toString());
            customer.setApellido2("");
            customer.setCedula(editcid.getText().toString());
            customer.setDireccion(editAddress.getText().toString());
            customer.setTelefonos(editphone.toString());

            if (spinnerCity.getSelectedIndex() != -1) {
                ciudad = listciudades.get(spinnerCity.getSelectedIndex());
                customer.setCiudad(ciudad.getNombre());
                customer.setObjCiudadID(ciudad.getStbCiudadID());
            }

            customer.setTelefonos(editphone.getText().toString());

            if (spinnerRuta.getSelectedIndex() != -1) {
                ruta = listrutas.get(spinnerRuta.getSelectedIndex());
                customer.setRutaAsignada(ruta.getNombre());
                customer.setStbRutaID(ruta.getStbRutaID());
            }

            if (spinnerCountry.getSelectedIndex() != -1) {
                pais = listpaises.get(spinnerCountry.getSelectedIndex());
                customer.setObjPaisID(pais.getStbPaisID());
                customer.setPais(pais.getNombre());
            }
            //customer.setObjGeneroID();

            if(male.isChecked()) {
                for (Catalog catalog : generos) {
                    if (catalog.getCodigo().equals("M")) {
                        customer.setGenero(catalog.getNombre());
                        customer.setObjGeneroID(catalog.getStbValorCatalogoID());
                        break;
                    }
                }
            }
            else {
                for (Catalog catalog : generos) {
                    if (catalog.getCodigo().equals("F")) {
                        customer.setGenero(catalog.getNombre());
                        customer.setObjGeneroID(catalog.getStbValorCatalogoID());
                        break;
                    }
                }
            }
            customer.setOjbCobradorID(((sifacApplication) getActivity().getApplication()).getUsuario());
            customer.setOrdenCobro(0);
            customer.setOffline(true);
            customer.save();
        }
        return cancel;
    }

    @OnClick(R.id.btncancelar)
    public void cancelar(){
        Intent intent = new Intent(getActivity(),CarteraListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//end Bundle
        startActivity(intent);


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


}
