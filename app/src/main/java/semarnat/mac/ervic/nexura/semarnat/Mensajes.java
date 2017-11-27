package semarnat.mac.ervic.nexura.semarnat;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pushbots.push.Pushbots;

public class Mensajes extends AppCompatActivity {
    private ObjetoMensaje[] datos;
    private MyRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    final Context context = this;
    private ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);
        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_mensajes);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Pushbots.sharedInstance().registerForRemoteNotifications();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        //Register for Push Notifications

        AppBarLayout appBar = (AppBarLayout) findViewById(R.id.app_bar);
        PopupMenu popup = new PopupMenu(appBar.getContext(), toolbar);



        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        Cursor fila = bd.rawQuery(
                "select * from messages", null);
        int k = 0;
        int numRows = (int) DatabaseUtils.longForQuery(bd, "SELECT COUNT(*) FROM messages", null);
        datos = new ObjetoMensaje[numRows];
        while(k < numRows){
            fila.moveToNext();
            Log.e("MESS_CODE["+k+"]: ",fila.getString(0));
            Log.e("MESS_TEXT["+k+"]: ",fila.getString(1));
            Log.e("MESS_DATE["+k+"]: ",fila.getString(2));
            datos[k] = new ObjetoMensaje(fila.getString(0),fila.getString(1),fila.getString(2));
            Log.e("DATOS",datos[k].getMess_code());
            k++;
        }
        if(numRows > 0){
            mRecyclerView.setAdapter(new MyRecyclerViewAdapter(Mensajes.this,datos));
        }

        /**
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,

                "tva", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();
        //bd.delete("user","",null);
        ContentValues registro = new ContentValues();
        registro.put("mess_code","1");
        registro.put("mess_text", "Bienvenido a TVA");
        registro.put("mess_date", "20171025033118");
        bd.insert("mensajes", null, registro);
        registro.clear();
        registro.put("mess_code","2");
        registro.put("mess_text", "Bienvenido Pastor Ever Lomelí");
        registro.put("mess_date", "20171025033118");
        bd.insert("mensajes", null, registro);
        registro.clear();
        registro.put("mess_code","3");
        registro.put("mess_text", "Con la app Rotary estas enterado de noticias relevantes, además cuentas con descuentos en más tiendas, buen día.");
        registro.put("mess_date", "20171025033118");
        bd.insert("mensajes", null, registro);

        Cursor c = bd.rawQuery("SELECT * FROM mensajes", null);
        int count = 0;
        if(c.moveToFirst()){
            do{
                datos[count].setMess_code(c.getString(0));
                datos[count].setMess_text(c.getString(1));
                datos[count].setMess_date(c.getString(2));

                count++;
            }while(c.moveToNext());

        }
         **/
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mensajes.this,Principal.class);
                startActivity(intent);
                finish();
            }
        });
        /**

         **/
        final GestureDetector mGestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                try {
                    View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());

                    if (child != null && mGestureDetector.onTouchEvent(e)) {

                        int position = mRecyclerView.getChildAdapterPosition(child);
                        //Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();


                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = getLayoutInflater();
                        final View dialoglayout = inflater.inflate(R.layout.despliega_mensaje,null);
                        final TextView dm_text = (TextView) dialoglayout.findViewById(R.id.dm_text);
                        final TextView dm_date = (TextView) dialoglayout.findViewById(R.id.dm_date);
                        builder.setView(dialoglayout);
                        ObjetoMensaje aux = datos[position];

                        dm_text.setText(aux.getMess_text());
                        String date = aux.getMess_date();
                        char year[] = new char[4];
                        date.getChars(0, 4, year, 0);
                        char month[] = new char[4];
                        date.getChars(4, 6, month, 0);
                        char day[] = new char[4];
                        date.getChars(6,8, day, 0);
                        dm_date.setText(String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day));

                        Log.e("TEXTO: ",aux.getMess_text());
                        Log.e("FECHA: ",String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day));
                        builder.create();
                        builder.show();
                        return true;
                    }
                }catch (Exception d){
//                  e.printStackTrace();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mensajes, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_descargar) {
            return true;


        }
        if(id == R.id.action_borrar){
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
            SQLiteDatabase bd = admin.getWritableDatabase();
            bd.delete(DatosDB.Table_messages.TABLE_MESSAGES,null,null);
            datos = new ObjetoMensaje[0];
            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_mensajes);
            mAdapter = new MyRecyclerViewAdapter(Mensajes.this,datos);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
