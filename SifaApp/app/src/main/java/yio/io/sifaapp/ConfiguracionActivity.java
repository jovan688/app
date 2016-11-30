package yio.io.sifaapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.Select;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yio.io.sifaapp.Venta.AppDialog;
import yio.io.sifaapp.model.Configuration;
import yio.io.sifaapp.model.ModelConfiguracion;
import yio.io.sifaapp.model.vmConfiguracion;

public class ConfiguracionActivity extends AppCompatActivity {

    @Bind(R.id.cfgtextv_detalleurlws)
    EditText cfgtextvDetalleurlws;
    @Bind(R.id.cfglbl_deviceid)
    TextView cfglblDeviceid;
    @Bind(R.id.cfglbl_codempresa)
    TextView cfglblCodempresa;
    @Bind(R.id.cfgtextv_detallecodempresa)
    EditText cfgtextvDetallecodempresa;
    @Bind(R.id.cfglbl_user)
    TextView cfglblUser;
    @Bind(R.id.cfgtextv_detalleuser)
    EditText cfgtextvDetalleuser;
    @Bind(R.id.btnsaveconfg)
    Button btnsaveconfg;
    @Bind(R.id.btnsignoff)
    Button btnsignoff;

    String device;
    @Bind(R.id.cfgtextv_detalledeviceid)
    EditText cfgtextvDetalledeviceid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        ButterKnife.bind(this);

        device = getDeviceId(this);
        initComponents();
    }


    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }


    private void initComponents() {

        vmConfiguracion model = ModelConfiguracion.getVMConfiguration(this);
        cfgtextvDetalleurlws.setText(model.getAppServerURL());
        cfgtextvDetalledeviceid.setText(model.getDeviceId());
        cfgtextvDetallecodempresa.setText(model.getEnterprise());
        //cfgtextvDetalleuser.setText(model.get);
        cfgtextvDetalledeviceid.setText(device);

    }

    @OnClick(R.id.btnsaveconfg)
    public void save() {


        vmConfiguracion model = new vmConfiguracion();
        model.setAppServerURL(cfgtextvDetalleurlws.getText().toString());
        model.setDeviceId(cfgtextvDetalledeviceid.getText().toString());
        model.setEnterprise(cfgtextvDetallecodempresa.getText().toString());

        try {
            ModelConfiguracion.saveConfiguration(this, model);

            AppDialog.showMessage(this, "", "Se ha Guardado Correctamente",
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.btnsignoff)
    public void singoff() {
        Configuration c = new Select().from(Configuration.class).querySingle();
        if (c != null) {
            c.setSession(false);
            c.save();
        }
        finish();

    }

}

