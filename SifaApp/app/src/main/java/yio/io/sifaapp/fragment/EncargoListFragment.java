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
import yio.io.sifaapp.EncargoNuevo.EncargoPresenter;
import yio.io.sifaapp.EncargoNuevo.EncargoPresenterIMP;
import yio.io.sifaapp.EncargoNuevo.IEncargoView;
import yio.io.sifaapp.R;
import yio.io.sifaapp.adapter.encargoAdapter;
import yio.io.sifaapp.model.Categoria;
import yio.io.sifaapp.model.Encargo;
import yio.io.sifaapp.model.Producto;


/**
 * A simple {@link Fragment} subclass.
 */
public class EncargoListFragment extends Fragment implements IEncargoView {

    private final String TAG = this.getClass().getSimpleName();
    View view = null;
    EncargoPresenter presenter;
    encargoAdapter encargoadapter;
    @Bind(R.id.EncargoRecyclerview)
    RecyclerView EncargoRecyclerview;
    List<Encargo> encargos = new ArrayList<Encargo>();

    public EncargoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_encargo_list, container, false);
        ButterKnife.bind(this, view);
        //setHasOptionsMenu(true);
        return view; //new RecyclerView(container.getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  ButterKnife.bind(this, view);
        presenter = new EncargoPresenterIMP(this);
        presenter.onCreated();

        InitAdapter();

        EncargoRecyclerview.setHasFixedSize(true);
        EncargoRecyclerview.setAdapter(encargoadapter);
        EncargoRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.getList();
    }

    private void InitAdapter() {
        if (encargoadapter == null) {
            encargoadapter = new encargoAdapter(getActivity(), new ArrayList<Encargo>());
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        presenter.onDestroy();
        Log.d(TAG,"onDestroyView : UnBind");
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
        Log.d(TAG,"onStop");

    }

    /* @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
        presenter.onDestroy();
        Log.d(TAG,"onDetach : UnBind");
    }*/

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
    public void fetchCategorias(List<Categoria> categorias) {

    }

    @Override
    public void fetchProductos(List<Producto> productos) {

    }

    @Override
    public void fetchEncargos(List<Encargo> encargos) {
        this.encargos = encargos;
        encargoadapter.setData(encargos);
        encargoadapter.notifyDataSetChanged();
    }


}
