package semarnat.mac.ervic.nexura.semarnat;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ervic on 20/10/17.
 */

public class ObjetoMensaje {
    @SerializedName("mess_code")
    private String mess_code;
    @SerializedName("mess_text")
    private String mess_text;
    @SerializedName("mess_date")
    private String mess_date;

    public ObjetoMensaje(String mess_code, String mess_text, String mess_date) {
        this.mess_code = mess_code;
        this.mess_text = mess_text;
        this.mess_date = mess_date;
    }

    public String getMess_code() {
        return mess_code;
    }

    public void setMess_code(String mess_code) {
        this.mess_code = mess_code;
    }

    public String getMess_text() {
        return mess_text;
    }

    public void setMess_text(String mess_text) {
        this.mess_text = mess_text;
    }

    public String getMess_date() {
        return mess_date;
    }

    public void setMess_date(String mess_date) {
        this.mess_date = mess_date;
    }
}
