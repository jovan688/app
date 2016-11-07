package yio.io.sifaapp.fragment;


import android.app.AlertDialog;
import android.content.Intent;
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
import yio.io.sifaapp.CarteraListActivity;
import yio.io.sifaapp.Login.ILoginView;
import yio.io.sifaapp.Login.LoginPresenter;
import yio.io.sifaapp.Login.LoginPresenterImplement;
import yio.io.sifaapp.R;
import yio.io.sifaapp.Venta.AppDialog;
import yio.io.sifaapp.model.ContadorModel;
import yio.io.sifaapp.utils.CustomDialog;
import yio.io.sifaapp.utils.TypeCounter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActualizarFragment extends Fragment implements IUpdateView , ILoginView {


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
    private LoginPresenter loginPresenter;
    int NOTIFICATION_DIALOG=0;
    private static CustomDialog dlg;

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

        loginPresenter = new LoginPresenterImplement(this , getActivity().getApplication());
        loginPresenter.onCreated();


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPurple), PorterDuff.Mode.SRC_IN);
        presenter.CountOfflineData();

        txtmessage.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.Server)
    public void download() {
        //setProgress("BAJANDO Lista del Sistema ", 100);
        loginPresenter.DownloadServer();
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
    public void enableInputs() {
        Server.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        Server.setEnabled(false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void authenticate() {
        showStatus("Se ha Descargado Exitosamente!",true);
    }

    @Override
    public void handleSignIn() {

    }

    @Override
    public void goToMainScreen() {
        hide();
        dlg = null;
        loginPresenter.onDestroy();
        Intent intent = new Intent(getActivity(), CarteraListActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginError(String message) {

    }

    @Override
    public void Sync(String message) {
        showStatus(message);
    }

    @Override
    public void onSingOff() {

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


    public void showStatus(final String mensaje, boolean... confirmacion) {

        if (confirmacion.length != 0 && confirmacion[0]) {
           getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppDialog.showMessage(getActivity(), "", mensaje,
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
        } else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dlg = new CustomDialog(getActivity(), mensaje, false, NOTIFICATION_DIALOG);
                    dlg.show();
                }
            });
        }
    }

    public void hide(){
        if (dlg != null && dlg.isShowing())
            dlg.dismiss();
    }

}
