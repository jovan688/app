package yio.io.sifaapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.Devolucion.IDevolucionListPresenter;
import yio.io.sifaapp.Devolucion.IDevolucionListPresenterImpl;
import yio.io.sifaapp.Devolucion.IDevolucionView;
import yio.io.sifaapp.R;
import yio.io.sifaapp.Venta.IventaListPresenterImpl;
import yio.io.sifaapp.adapter.devolucionAdapter;
import yio.io.sifaapp.adapter.ventaAdapter;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.Devolucion;
import yio.io.sifaapp.model.DevolucionProductos;
import yio.io.sifaapp.model.modelSend.Venta;


/**
 * A simple {@link Fragment} subclass.
 */
public class DevolucionListFragment extends Fragment implements IDevolucionView {

    private static final String TAG = DevolucionListFragment.class.getSimpleName();

    View view = null;
    @Bind(R.id.DevolucionRecyclerview)
    RecyclerView DevolucionRecyclerview;
    devolucionAdapter adapter = null;
    IDevolucionListPresenter presenter;
    List<Devolucion> list;
    Customer customer;

    public DevolucionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_devolucion_list, container, false);
        ButterKnife.bind(this, view);
        //setHasOptionsMenu(true);
        return view; //new RecyclerView(container.getContext());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        presenter = new IDevolucionListPresenterImpl(this);
        presenter.onCreated();

        InitAdapter();

        DevolucionRecyclerview.setHasFixedSize(true);
        DevolucionRecyclerview.setAdapter(adapter);
        DevolucionRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.getList();
        // SetBottomOptions(savedInstanceState);
    }
    private void InitAdapter() {
        if (adapter == null) {
            adapter = new devolucionAdapter(getActivity(), new ArrayList<Devolucion>());
        }
    }

    @Override
    public void onStop() {
        presenter.onDestroy();
        super.onStop();
        Log.d(TAG,"onStop");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
        list = devolucions;
        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCustomer(Customer c) {
        customer = c;
        adapter.setCustomer(c);
    }

    @Override
    public void obtenerDetalle(List<DevolucionProductos> detalle) {

    }


}
