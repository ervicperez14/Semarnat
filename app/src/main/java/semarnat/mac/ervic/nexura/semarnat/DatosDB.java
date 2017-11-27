package semarnat.mac.ervic.nexura.semarnat;

import android.provider.BaseColumns;

/**
 * Created by ervic on 01/11/17.
 */

public class DatosDB {
    private DatosDB(){}
    String tabla_uno = "create table  if not exists user(user_code integer, user_name text, user_mail text, user_barcode int, user_cellphone int, user_registered int)";
    String tabla_dos = "create table  if not exists messages(mess_code INTEGER PRIMARY KEY AUTOINCREMENT, mess_text text, mess_date text)";

    public static class Table_user implements BaseColumns

    {
        public static final String TABLE_USER = "user";
        public static final String COLUMN_NAME_USER_CODE = "user_code";
        public static final String COLUMN_NAME_USER_NAME = "user_name";
        public static final String COLUMN_NAME_USER_EMAIL = "user_mail";
        public static final String COLUMN_NAME_USER_BAR_CODE = "user_barcode";
        public static final String COLUMN_NAME_USER_CELLPHONE = "user_cellphone";
        public static final String COLUMN_NAME_USER_REGISTERED = "user_registered";


    }
    public static class Table_messages implements BaseColumns

    {
        public static final String TABLE_MESSAGES = "messages";
        public static final String COLUMN_NAME_MESS_CODE = "mess_code";
        public static final String COLUMN_NAME_MESS_TEXT= "mess_text";
        public static final String COLUMN_NAME_MESS_DATE = "mess_date";



    }

}
