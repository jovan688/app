package yio.io.sifaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.Cartera.CarteraListPresenterImplement;
import yio.io.sifaapp.Cartera.ICarteraListPresenter;
import yio.io.sifaapp.Cartera.IDetallaCarteraView;
import yio.io.sifaapp.Catalogos.CatalogoPresenter;
import yio.io.sifaapp.Catalogos.CatalogoPresenterIMP;
import yio.io.sifaapp.Catalogos.ICatalogoView;
import yio.io.sifaapp.R;
import yio.io.sifaapp.adapter.CustomerAdapter;
import yio.io.sifaapp.adapter.OnItemClickListener;
import yio.io.sifaapp.adapter.OnStartDragListener;
import yio.io.sifaapp.adapter.SimpleItemTouchHelperCallback;
import yio.io.sifaapp.model.Cartera;
import yio.io.sifaapp.model.Catalog;
import yio.io.sifaapp.model.Ciudad;
import yio.io.sifaapp.model.Descuento;
import yio.io.sifaapp.model.Pais;
import yio.io.sifaapp.model.Ruta;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarteraListFragment extends Fragment implements ICatalogoView, IDetallaCarteraView, OnStartDragListener, OnItemClickListener {

    CustomerAdapter adapter = null;
    View view = null;
    @Bind(R.id.CustomerRecyclerview)
    RecyclerView CustomerRecyclerview;
    List<Cartera> list = null;
    ICarteraListPresenter presenter;
    CatalogoPresenter catalogoPresenter;

    List<Ruta> rutas;
    @Bind(R.id.spinner_ruta)
    MaterialSpinner spinnerRuta;
    @Bind(R.id.txtviewTotal)
    TextView txtviewTotal;
    private ItemTouchHelper mItemTouchHelper;


    public CarteraListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cartera_list, container, false);
        ButterKnife.bind(this, view);
        //setHasOptionsMenu(true);
        return view; //new RecyclerView(container.getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        presenter = new CarteraListPresenterImplement(this);
        presenter.onCreated();


        InitAdapter();

        CustomerRecyclerview.setHasFixedSize(true);
        CustomerRecyclerview.setAdapter(adapter);
        CustomerRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(CustomerRecyclerview);

        catalogoPresenter = new CatalogoPresenterIMP(this);
        catalogoPresenter.onCreated();
        catalogoPresenter.getRutas();

    }

    private void InitAdapter() {
        if (adapter == null) {
            adapter = new CustomerAdapter(getActivity(), this, new ArrayList<Cartera>(), this, this);
        }
    }


    @Override
    public void setCarteraList(List<Cartera> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void carteraupdated(int fromPosition, int toPosition, Cartera cartera) {
        Log.d("carteraupdated", cartera.getNombreCompleto());
        presenter.updatedCartera(fromPosition, toPosition, cartera);
    }

    @Override
    public void carteraDeleted(Cartera cartera) {

    }

    @Override
    public void updateAmount(Float amount) {
        txtviewTotal.setText(txtviewTotal.getText()+ " "+ String.valueOf(amount));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void getFragment(Cartera cartera) {
        presenter.onDestroy();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        DetalleClienteFragment detalle = new DetalleClienteFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Cartera", cartera);
        detalle.setArguments(bundle);
        manager.beginTransaction().replace(R.id.fragment_container, detalle).commit();


    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }


    public void OnClick_btnNewCustomer() {
        Fragment fragment = new NuevoClienteFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }
    /*
    public void OnClick_btnNewSale() {
        Fragment fragment = new NuevoVentaFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
    */

    @Override
    public void onSeleted(Object object) {
        Log.d("CarteraListFrament", "onSeleted");
        getFragment((Cartera) object);
    }

    @Override
    public void fetchPais(List<Pais> paises) {

    }

    @Override
    public void fechCiudades(List<Ciudad> ciudades) {

    }

    @Override
    public void fetchRutas(final List<Ruta> rutas) {

        this.rutas = rutas;

        //  ArrayAdapter<Ruta> adapter = new ArrayAdapter<Ruta>(getContext(), android.R.layout.simple_dropdown_item_1line, rutas);
        if (spinnerRuta != null) {
            spinnerRuta.setItems(rutas);
            spinnerRuta.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                    Ruta r = rutas.get(position);
                    presenter.getCarteraList(r.getStbRutaID());
                }
            });
            spinnerRuta.setTextColor(getResources().getColor(R.color.colorPurple));
            Ruta r = rutas.get(0);
            presenter.getCarteraList(r.getStbRutaID());
            presenter.GetAmountCobrado(r.getStbRutaID());
        }

    }

    @Override
    public void fetchGeneros(List<Catalog> generos) {

    }

    @Override
    public void fetchCuotas(List<Catalog> cuotas) {

    }

    @Override
    public void fetchPlazos(List<Catalog> plazos) {

    }

    @Override
    public void fetchDescuentos(List<Descuento> descuentos) {

    }
}

