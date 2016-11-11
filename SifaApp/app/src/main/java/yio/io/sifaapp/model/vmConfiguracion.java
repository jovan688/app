package yio.io.sifaapp.model;

/**
 * Created by JUANCARLOS on 10/11/2016.
 */
public class vmConfiguracion {

    private java.lang.String URL_SERVER;
    private java.lang.String URL_SERVER2;
    private java.lang.String DEVICE_ID;
    private java.lang.String ENTERPRISE;
    private java.lang.String NAME_USER;
    private int MAX_IDPEDIDO;
    private int MAX_IDRECIBO;
    private int MAX_IDDEVOLUCIONV;
    private int MAX_IDDEVOLUCIONNV;

    public static vmConfiguracion setConfiguration(java.lang.String url_server,
                                                   java.lang.String device_prefix, java.lang.String enterprise
                                                  ) {

        vmConfiguracion vmonfig = new vmConfiguracion();
        vmonfig.setAppServerURL(url_server);
        vmonfig.setDeviceId(device_prefix);
        vmonfig.setEnterprise(enterprise);


        return vmonfig;
    }
    public static vmConfiguracion setConfiguration(vmConfiguracion obj)
    {
        return vmConfiguracion.setConfiguration(obj.getAppServerURL(),
                obj.getDeviceId(), obj.getEnterprise());
    }

    public String setAppServerURL(String uri) {
        return this.URL_SERVER=uri;
    }

    public String getAppServerURL() {
        return this.URL_SERVER;
    }

    public String setDeviceId(String id_celular) {
        return this.DEVICE_ID=id_celular;
    }

    public String getDeviceId() {
        return this.DEVICE_ID;
    }
    public String setEnterprise(String empresa) {
        return this.ENTERPRISE=empresa;
    }

    public String getEnterprise() {
        return this.ENTERPRISE;
    }

}
