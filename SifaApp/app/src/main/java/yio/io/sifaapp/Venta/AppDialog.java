package yio.io.sifaapp.Venta;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import yio.io.sifaapp.R;
import yio.io.sifaapp.model.Cartera;

import static yio.io.sifaapp.Venta.AppDialog.txtmontovariado;

/**
 * Created by JUANCARLOS on 27/08/2016.
 */
public class AppDialog extends DialogFragment implements OnDismissListener {

    static TextView label;
    static TextView txttitle;

    static RadioButton radiocuota;

    static RadioButton radiocuotavariada;

    static EditText txtmontovariado;

    static RadioGroup radioAbono;

    static EditText motivo;

    static Button btnabonar;

    static Float cuota;
    Cartera cartera;

    private Context cnt;
    public static final int OK_BUTTOM = 11;
    public static final int NO_BUTTOM = 22;
    static Object lock = new Object();
    static String Message = null;
    static String Tittle = null;
    private static FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public static enum DialogType {
        DIALOGO_NOTIFICACION(0),
        DIALOGO_ALERTA(1),
        DIALOGO_CONFIRMACION(2),
        DIALOGO_SELECCION(3),
        DIALOGO_DINAMICO(4),
        DIALOGO_INPUT(5),
        DIALOGO_OCADISCOUNT(6),
        DIALOGO_NOTIFICACION2(10),
        DIALOGO_ABONO(7),
        DIALOGO_NO_ABONO(8);
        int result;

        DialogType(int result) {
            this.result = result;
        }

        public int getActionCode() {
            return result;
        }

        public static DialogType toInt(int x) {
            return DialogType.values()[x];
        }
    }

    static AlertDialog alert = null;
    static View vDialog;
    static Builder mybuilder;
    static LayoutInflater inflater;
    static TextView tvtittle;
    static TextView tvmessage;
    static Button btn_aceptar;
    static Button btn_cancelar;
    static EditText txtpayamount;
    static EditText tbox_discoutnkey;
    static EditText tbox_percentcollector;
    public OnButtonClickListener mButtonClickListener;
    private OnDismissDialogListener mDismissListener;
    public OnAbonoClickListener mAbonoClickListener;

    public interface OnButtonClickListener {
        public abstract void onButtonClick(AlertDialog alert, int actionId);
    }
    public interface OnAbonoClickListener {
        public abstract void onButtonClick( int actionId , Object o);
    }
    public interface OnDismissDialogListener {
        public abstract void onDismiss();
    }
    public void setOnDialogButtonClickListener(OnAbonoClickListener listener) {
        mAbonoClickListener = listener;
    }
    public void setOnDialogButtonClickListener(OnButtonClickListener listener) {
        mButtonClickListener = listener;
    }

    public void setOnDismissDialogListener(OnDismissDialogListener listener) {
        mDismissListener = listener;
    }

    public static void showMessage(Context mContext, String msg, DialogType type, OnButtonClickListener onButtonClickListener) {
        mybuilder = new Builder(mContext);
        //inflater = mContext.getLayoutInflater();
        inflater = LayoutInflater.from(mContext);
        Message = msg;
        switch (type) {
            case DIALOGO_ALERTA:
                CreateDialog(onButtonClickListener);
                break;
            case DIALOGO_CONFIRMACION:
                CreateConfirmDialog(onButtonClickListener);
                break;
            /*case DIALOGO_ABONO:
                CreateAbonoDialog(true,onButtonClickListener);
                break;
            case DIALOGO_NO_ABONO:
                CreateAbonoDialog(false,onButtonClickListener);
                break;*/
            default:
                break;

        }
        alert = mybuilder.create();
        alert.setCancelable(false);
        int margen = -2;
        alert.setView(vDialog, margen, margen, margen, margen);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.show();
    }

    public static void showMessage(Context mContext, String msg, DialogType type, OnAbonoClickListener onButtonClickListener) {
        mybuilder = new Builder(mContext);
        //inflater = mContext.getLayoutInflater();
        inflater = LayoutInflater.from(mContext);
        Message = msg;
        switch (type) {
            case DIALOGO_ABONO:
                CreateAbonoDialog(true,onButtonClickListener);
                break;
            case DIALOGO_NO_ABONO:
                CreateAbonoDialog(false,onButtonClickListener);
                break;
            default:
                break;

        }
        alert = mybuilder.create();
        alert.setCancelable(true);
        int margen = -2;
        alert.setView(vDialog, margen, margen, margen, margen);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.show();
    }

    public static void showMessage(Context mContext, String title, String msg, DialogType type) {
        mybuilder = new Builder(mContext);

        //inflater = mContext.getLayoutInflater();
        inflater = LayoutInflater.from(mContext);
        Tittle = title;
        Message = msg;
        switch (type) {
            case DIALOGO_ALERTA:
                CreateDialog(null);
                break;
            case DIALOGO_CONFIRMACION:
                CreateConfirmDialog(null);
                break;
            default:
                break;

        }
        alert = mybuilder.create();
        if (type == DialogType.DIALOGO_ALERTA)
            alert.setCancelable(true);
        else
            alert.setCancelable(false);
        int margen = -2;
        alert.setView(vDialog, margen, margen, margen, margen);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.show();
    }

    public static AlertDialog _showMessage(Context mContext, String title, String msg, DialogType type, final OnButtonClickListener mylistener) {
        mybuilder = new Builder(mContext);
        //inflater = mContext.getLayoutInflater();
        inflater = LayoutInflater.from(mContext);
        Tittle = title;
        Message = msg;
        switch (type) {
            case DIALOGO_ALERTA:
                CreateDialog(mylistener);
                break;
            case DIALOGO_CONFIRMACION:
                CreateConfirmDialog(mylistener);
                break;
            default:
                break;

        }
        //mybuilder.setView(vDialog);
        alert = mybuilder.create();
        alert.setCancelable(false);
        int margen = -2;
        alert.setView(vDialog, margen, margen, margen, margen);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return alert;
    }

    public static void showMessage(Context mContext, String title, String msg, DialogType type, final OnButtonClickListener mylistener) {
        mybuilder = new Builder(mContext);
        //inflater = mContext.getLayoutInflater();
        inflater = LayoutInflater.from(mContext);
        Tittle = title;
        Message = msg;
        switch (type) {
            case DIALOGO_ALERTA:
                CreateDialog(mylistener);
                break;
            case DIALOGO_CONFIRMACION:
                CreateConfirmDialog(mylistener);
                break;
            default:
                break;

        }
        //mybuilder.setView(vDialog);
        alert = mybuilder.create();
        alert.setCancelable(true);
        int margen = -2;
        alert.setView(vDialog, margen, margen, margen, margen);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.show();
    }

    private static void CreateConfirmDialog(final OnButtonClickListener mylistener) {
        vDialog = inflater.inflate(R.layout.confirm_dialog2, null, false);
        tvmessage = (TextView) vDialog.findViewById(R.id.message_dialog);
        tvmessage.setText(Message.toString());
        btn_aceptar = (Button) vDialog.findViewById(R.id.btnaceptar_dialog_confirm);
        btn_aceptar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mylistener != null)
                    mylistener.onButtonClick(alert, OK_BUTTOM);
                alert.dismiss();
            }
        });
        btn_cancelar = (Button) vDialog.findViewById(R.id.btncancelar_dialog_confirm);
        btn_cancelar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylistener.onButtonClick(alert, NO_BUTTOM);
                alert.dismiss();
            }
        });

    }

    private static void CreateDialog( final OnButtonClickListener mylistener) {
        try {
            vDialog = inflater.inflate(R.layout.alert_dialog2, null, false);
            tvmessage = (TextView) vDialog.findViewById(R.id.bodymessage_dialog_alert);
            tvmessage.setText(Message.toString());
            btn_aceptar = (Button) vDialog.findViewById(R.id.btnaceptar_dialog_alert);
            btn_aceptar.setVisibility(View.INVISIBLE);
            ((Builder) mybuilder).setNeutralButton("Aceptar", new DialogInterface.OnClickListener() { // define the 'Cancel' button
                public void onClick(DialogInterface dialog, int which) {
                    //Either of the following two lines should work.
                    if (mylistener != null) {
                        mylistener.onButtonClick(alert, OK_BUTTOM);
                    }
                    dialog.cancel();
                    //dialog.dismiss();
                }
            });
            /*btn_aceptar.setOnClickListener(new Button.OnClickListener()
	    	{
				@Override
				public void onClick(View v) {
					if(mylistener!=null){
						mylistener.onButtonClick(alert, OK_BUTTOM);
					}
					if(alert != null && alert.isShowing())
						alert.dismiss();
				}
	    	});*/
			/*mybuilder.setView(vDialog);
			alert = mybuilder.create();*/

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private static void CreateAbonoDialog(Boolean abono ,final OnAbonoClickListener mylistener) {
        vDialog = inflater.inflate(R.layout.abonodialog, null, false);
        txttitle = (TextView) vDialog.findViewById(R.id.txttitle);
        label = (TextView) vDialog.findViewById(R.id.label);
        motivo = (EditText) vDialog.findViewById(R.id.motivo);
        txtmontovariado = (EditText) vDialog.findViewById(R.id.txtmontovariado);
        radiocuota =  (RadioButton)vDialog.findViewById(R.id.radiocuota);
        radiocuotavariada =  (RadioButton)vDialog.findViewById(R.id.radiocuotavariada);
        btn_aceptar = (Button) vDialog.findViewById(R.id.btnabonar);

        btn_aceptar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mylistener != null){
                   // String cuota = txtmontovariado.getText().equals("0") ? radiocuota.getText().toString() : txtmontovariado.getText().toString();
                    if(radiocuota.isChecked()== false )  cuota = Float.parseFloat(txtmontovariado.getText().toString());
                    mylistener.onButtonClick(OK_BUTTOM, cuota );
                }
                alert.dismiss();
            }
        });

        btn_cancelar = (Button) vDialog.findViewById(R.id.btncancelar_dialog_confirm);
        if (btn_cancelar != null) {
            btn_cancelar.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.onButtonClick(NO_BUTTOM, motivo.getText().toString());
                    alert.dismiss();
                }
            });
        }

        radioAbono = (RadioGroup) vDialog.findViewById(R.id.radioAbono);
        if(radioAbono!=null){
            radioAbono.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.radiocuota){
                        txtmontovariado.setText("0");
                        txtmontovariado.setEnabled(false);

                    }
                    else {
                        txtmontovariado.setEnabled(true);
                    }
                }
            });
        }

        txtmontovariado.setEnabled(false);
        if(abono== false ) {
            txttitle.setText("NO ABONO");
            label.setVisibility(View.VISIBLE);
            radioAbono.setVisibility(View.GONE);
        }
    }


    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance())
            getDialog().setOnDismissListener(null);
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mDismissListener.onDismiss();
    }

    public static void  setRadiocuota(String amount){
        radiocuota.setText(amount);
        cuota = Float.parseFloat(amount);
    }
}
