package yio.io.sifaapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    static Context c ;
    String device;
    @Bind(R.id.cfgtextv_detalledeviceid)
    EditText cfgtextvDetalledeviceid;
    final int  MY_PERMISSIONS_REQUEST_READ_PHONE_STATE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        ButterKnife.bind(this);
        c = getApplicationContext();
         getDeviceId(this);
        initComponents();
    }


    public void getDeviceId(Context context) {
        if(Build.VERSION_CODES.M >= Build.VERSION.SDK_INT) {




            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(c,
                    Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {


                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_PHONE_STATE)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    Toast.makeText(c,"Se necesita este Permiso para Sincronizar con el Servidor", Toast.LENGTH_SHORT).show();


                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_PHONE_STATE},
                            MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
            else {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                device = tm.getDeviceId();

            }
        }
        else
        {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            device = tm.getDeviceId();
        }
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
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    TelephonyManager tm = (TelephonyManager) getApplication().getSystemService(Context.TELEPHONY_SERVICE);
                    device = tm.getDeviceId();
                    cfgtextvDetalledeviceid.setText(device);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Configuration c = new Select().from(Configuration.class).querySingle();
            if (c != null) {
                c.setSession(false);
                c.save();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

