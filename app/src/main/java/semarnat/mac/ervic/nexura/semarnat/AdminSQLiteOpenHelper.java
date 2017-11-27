package semarnat.mac.ervic.nexura.semarnat;

/**
 * Created by ervic on 21/10/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    String tabla_uno = "create table  if not exists user(user_code integer, user_name text, user_mail text, user_barcode int, user_cellphone int, user_registered int)";
    String tabla_dos = "create table  if not exists messages(mess_code INTEGER PRIMARY KEY AUTOINCREMENT, mess_text text, mess_date text)";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tva.db";
    private static final String SQL_CREATE_TABLE_USER =
            "CREATE TABLE " + DatosDB.Table_user.TABLE_USER + " (" +
                    DatosDB.Table_user.COLUMN_NAME_USER_CODE + " INTEGER PRIMARY KEY," +
                    DatosDB.Table_user.COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP +
                    DatosDB.Table_user.COLUMN_NAME_USER_EMAIL + TEXT_TYPE + COMMA_SEP +
                    DatosDB.Table_user.COLUMN_NAME_USER_BAR_CODE + TEXT_TYPE + COMMA_SEP +
                    DatosDB.Table_user.COLUMN_NAME_USER_CELLPHONE + TEXT_TYPE + COMMA_SEP +
                    DatosDB.Table_user.COLUMN_NAME_USER_REGISTERED + TEXT_TYPE + " )";

    private static final String SQL_DELETE_TABLE_MESSAGES =
            "DROP TABLE IF EXISTS " + DatosDB.Table_user.TABLE_USER;

    private static final String SQL_CREATE_TABLE_MESSAGES =
            "CREATE TABLE " + DatosDB.Table_messages.TABLE_MESSAGES + " (" +
                    DatosDB.Table_messages.COLUMN_NAME_MESS_CODE + " INTEGER PRIMARY KEY," +
                    DatosDB.Table_messages.COLUMN_NAME_MESS_TEXT + TEXT_TYPE + COMMA_SEP +
                    DatosDB.Table_messages.COLUMN_NAME_MESS_DATE + TEXT_TYPE + " )";

    private static final String SQL_DELETE_TABLE_USER =
            "DROP TABLE IF EXISTS " + DatosDB.Table_messages.TABLE_MESSAGES;


    public AdminSQLiteOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        //aquí creamos la tabla de usuario (dni, nombre, ciudad, numero)
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_MESSAGES);

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {

        db.execSQL(SQL_DELETE_TABLE_USER);
        db.execSQL(SQL_DELETE_TABLE_MESSAGES);

        onCreate(db);

    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}