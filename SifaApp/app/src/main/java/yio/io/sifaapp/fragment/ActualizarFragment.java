package yio.io.sifaapp.fragment;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yio.io.sifaapp.Actualizacion.IUpdatePresenter;
import yio.io.sifaapp.Actualizacion.IUpdateView;
import yio.io.sifaapp.Actualizacion.UpdatePresenterImpl;
import yio.io.sifaapp.R;
import yio.io.sifaapp.model.ContadorModel;
import yio.io.sifaapp.utils.TypeCounter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActualizarFragment extends Fragment implements IUpdateView {


    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.Local)
    ImageButton Local;
    @Bind(R.id.Server)
    ImageButton Server;
    @Bind(R.id.txtmessage)
    TextView txtmessage;
    @Bind(R.id.cateranum)
    TextView cateranum;
    @Bind(R.id.clientenum)
    TextView clientenum;
    @Bind(R.id.ventaenum)
    TextView ventaenum;
    @Bind(R.id.devolucionnum)
    TextView devolucionnum;
    @Bind(R.id.encargonum)
    TextView encargonum;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private IUpdatePresenter presenter;


    ContadorModel contador = null;

    public ActualizarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actualizar, container, false);
        ButterKnife.bind(this, view);
        presenter = new UpdatePresenterImpl(this ,getActivity().getApplicationContext());
        presenter.onCreated();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPurple), PorterDuff.Mode.SRC_IN);
        presenter.CountOfflineData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.Server)
    public void download() {
        setProgress("BAJANDO Lista del Sistema ", 100);
    }

    @OnClick(R.id.Local)
    public void upload() {
        //setProgress("SUBIENDO Lista del Sistema ");
       // if(contador.getCartera()!= 0  contador.getClientes()!=0 && contador.getDevoluciones()!= 0 && contador.getVentas()!= 0  && contador.getEncargos() != 0 ) {
        progressBar.setProgress(progressStatus);
        presenter.UpdateCliente();
    }


    private void setProgress(final String label ,final int counter) {
        progressStatus = 0;
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < counter) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            txtmessage.setText(label.concat(progressStatus + "/" + progressBar.getMax()));
                            int c = counter - progressStatus;
                            cateranum.setText(String.valueOf(c));
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    @Override
    public void enableButtons() {
        Local.setEnabled(true);
    }

    @Override
    public void disableButtons() {
        Local.setEnabled(false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void UpdateCounter(TypeCounter type, int count) {
        if(type == TypeCounter.CLIENTE)
            clientenum.setText(String.valueOf(count));
        if(type == TypeCounter.DEVOLUCION)
            devolucionnum.setText(String.valueOf(count));
        if(type == TypeCounter.VENTA)
            ventaenum.setText(String.valueOf(count));
        if(type == TypeCounter.ENCARGO)
            encargonum.setText(String.valueOf(count));
        if(type == TypeCounter.COBRO)
            cateranum.setText(String.valueOf(count));
    }

    @Override
    public void ClienteCounter(int count) {
        clientenum.setText(String.valueOf(count));
    }

    @Override
    public void notify(String message) {
        txtmessage.setText(message);
    }

    @Override
    public void UpdateVentas() {
        presenter.UpdateVentas();
    }

    @Override
    public void UpdateEncargos() {
        presenter.UpdateEncargos();
    }

    @Override
    public void UpdateDevoluciones() {
        presenter.UpdateDevoluciones();
    }

    @Override
    public void UpdateCartera() {
        presenter.UpdateCartera();
    }

    @Override
    public void ShowError(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CountOfflineData(ContadorModel contadores) {
        contador = contadores;
        cateranum.setText(String.valueOf(contadores.getCartera()));
        encargonum.setText(String.valueOf(contadores.getEncargos()));
        ventaenum.setText(String.valueOf(contadores.getVentas()));
        clientenum.setText(String.valueOf(contadores.getClientes()));
        devolucionnum.setText(String.valueOf(contadores.getDevoluciones()));

        if(contador.getCartera()== 0 && contador.getClientes()==0 && contador.getDevoluciones()== 0 && contador.getVentas()== 0  && contador.getEncargos() == 0 ) {
            Server.setEnabled(true);
        }
        else {
            Server.setEnabled(false);
        }
    }

    public void showprogress(String label ){
        progressBar.setProgress(progressStatus);
        txtmessage.setText(label.concat(progressStatus + "/" + progressBar.getMax()));

    }

}
