package yio.io.sifaapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.Cartera.IVentaView;
import yio.io.sifaapp.R;
import yio.io.sifaapp.Venta.IventaListPresenter;
import yio.io.sifaapp.Venta.IventaListPresenterImpl;
import yio.io.sifaapp.adapter.OnItemClickListener;
import yio.io.sifaapp.adapter.ventaAdapter;
import yio.io.sifaapp.model.Customer;
import yio.io.sifaapp.model.modelSend.Venta;


/**
 * A simple {@link Fragment} subclass.
 */
public class VentaListFragment extends Fragment implements OnItemClickListener, IVentaView {

    View view = null;
    @Bind(R.id.SalesRecyclerview)
    RecyclerView SalesRecyclerview;
    @Bind(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    private BottomBar bottomBar;
    ventaAdapter adapter = null;
    IventaListPresenter presenter;


    public VentaListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_venta_list, container, false);
        ButterKnife.bind(this, view);
        //setHasOptionsMenu(true);
        return view; //new RecyclerView(container.getContext());

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        presenter = new IventaListPresenterImpl(this);
        presenter.onCreated();

        InitAdapter();

        SalesRecyclerview.setHasFixedSize(true);
        SalesRecyclerview.setAdapter(adapter);
        SalesRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.getList();
        // SetBottomOptions(savedInstanceState);
    }


    private void InitAdapter() {
        if (adapter == null) {
            adapter = new ventaAdapter(getActivity(), new ArrayList<Venta>());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

    }
    @Override
    public void onStop() {
        //presenter.onDestroy();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSeleted(Object object) {

    }


    @Override
    public void onDataFetch(List<Venta> ventas) {
        adapter.setData(ventas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCustomer(Customer c) {
        adapter.setCustomer(c);
    }


}
