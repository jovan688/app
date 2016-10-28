package yio.io.sifaapp.utils;

/**
 * Created by JUANCARLOS on 07/08/2016.
 */
public class ResponseMessage {

    private String title;
    private String message;
    private String cause;

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    private boolean hasError;

    private static ResponseMessage error;

    public static ResponseMessage newInstance(String title, boolean iserror, String message, String cause)
    {
        if(error==null)
            error = new ResponseMessage(title, iserror,message, cause);
        return error;
    }

    public ResponseMessage(String title, boolean iserror, String cause, String message) {
        this.title = title;
        this.hasError = iserror;
        this.cause = cause;
        this.message = message;
    }

    public void setTittle(String title){
        this.title=title;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public void setCause(String cause){
        this.cause=cause;
    }
    public String getTittle(){
        return this.title;
    }
    public String getMessage(){
        return this.message;
    }
    public String getCause(){
        return this.cause;
    }
}
