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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yio.io.sifaapp.Actualizacion.IUpdatePresenter;
import yio.io.sifaapp.Actualizacion.IUpdateView;
import yio.io.sifaapp.Actualizacion.UpdatePresenterImpl;
import yio.io.sifaapp.R;

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
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private IUpdatePresenter presenter;

    public ActualizarFragment() {
        // Required empty public constructor
        presenter = new UpdatePresenterImpl(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actualizar, container, false);
        ButterKnife.bind(this, view);
        presenter.onCreated();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPurple), PorterDuff.Mode.SRC_IN);
        presenter.getCLienteContador();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.Server)
    public void download() {
        setProgress("BAJANDO Lista del Sistema ");
    }

    @OnClick(R.id.Local)
    public void upload() {
        //setProgress("SUBIENDO Lista del Sistema ");
        presenter.UpdateCliente();
    }


    private void setProgress(final String label) {
        progressStatus = 0;
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            txtmessage.setText(label.concat(progressStatus + "/" + progressBar.getMax()));
                            int c = 100 - progressStatus;
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

    }

    @Override
    public void disableButtons() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void UpdateCartera() {

    }

    @Override
    public void UpdateCliente() {

    }

    @Override
    public void UpdateVentas() {

    }

    @Override
    public void UpdateDevoluciones() {

    }

    @Override
    public void UpdateEncargos() {

    }

    @Override
    public void UpdateError(String message) {

    }

    @Override
    public void Sync(String message) {

    }

    @Override
    public void UpdateCounter(int type, int count) {
        clientenum.setText(String.valueOf(count));
    }

    @Override
    public void ClienteCounter(int count) {
        clientenum.setText(String.valueOf(count));
    }
}
